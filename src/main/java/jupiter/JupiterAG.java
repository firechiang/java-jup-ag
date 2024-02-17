package jupiter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.QuoteResponse;
import utils.SwapTransactionResponse;
import utils.SwapTransactionRequest;
import utils.Token;

public class JupiterAG {

  private OkHttpClient client;
  private final String JUPITER_URL = "https://token.jup.ag/";
  private final String JUPITER_PRICE_API_URL = "https://price.jup.ag/v4/price";
  private final String JUPITER_QUOTE_API_URL = "https://quote-api.jup.ag/v6/quote";
  private final String JUPITER_TRANSACTION_API_URL = "https://quote-api.jup.ag/v6/swap";

  public JupiterAG() {
    this.client = new OkHttpClient();
  }

  public Token[] getTokens(boolean strict) throws IOException {
    Request request = new Request.Builder().url(strict ? JUPITER_URL + "strict" : JUPITER_URL + "all").build();
    Response response = client.newCall(request).execute();

    Token[] tokens = new Gson().fromJson(response.body().string(), Token[].class);

    return tokens;
  }

  /**
   * Returns the unit price of the token based on the buy amount of 1 unit in
   * USDC.
   * 
   * @param token - contract-address for token
   * @return price in USDC
   * @throws IOException
   */
  public double getTokenPrice(String token) throws IOException {
    if (StringUtils.isBlank(token))
      throw new IllegalArgumentException("Token cannot be null or an empty string.");

    Request request = new Request.Builder().url(JUPITER_PRICE_API_URL + "?ids=" + token).build();
    Response response = client.newCall(request).execute();

    double price = -1;

    try {
      JSONObject json = new JSONObject(response.body().string());
      price = json.getJSONObject("data").getJSONObject(token).getDouble("price");
    } catch (JSONException e) {
      throw new JSONException("Unable to determine price.");
    }

    return price;
  }

  public QuoteResponse getQuote(String inputMint, String outputMint, long amount, long slippageBps) throws IOException {
    Request request = new Request.Builder().url(JUPITER_QUOTE_API_URL + "?inputMint=" + inputMint + "&outputMint="
        + outputMint + "&amount=" + amount + "&slippageBps=" + slippageBps).build();
    Response response = client.newCall(request).execute();

    QuoteResponse quoteResponse = null;

    try {
      quoteResponse = new Gson().fromJson(response.body().string(), QuoteResponse.class);
    } catch (JSONException e) {
      throw new JSONException("Unable to retrieve quote.");
    }
    
    return quoteResponse;
  }

  public SwapTransactionResponse getSwapTransaction(QuoteResponse quoteResponse, String pubKey) throws IOException {
    SwapTransactionRequest swapTransactionRequest = new SwapTransactionRequest();
    swapTransactionRequest.setQuoteResponse(quoteResponse);
    swapTransactionRequest.setUserPublicKey(pubKey);
        
    RequestBody body = RequestBody.create(
        MediaType.parse("application/json"), new Gson().toJson(swapTransactionRequest));
    
    
    Request request = new Request.Builder()
        .url(JUPITER_TRANSACTION_API_URL)
        .post(body)
        .build();
    
    
    Response response = client.newCall(request).execute();

    return new Gson().fromJson(response.body().string(), SwapTransactionResponse.class);
  }

}
