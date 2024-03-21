package jupiter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.p2p.solanaj.core.PublicKey;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.MessageAddressTableLookup;
import utils.QuoteResponse;
import utils.SwapTransactionResponse;
import utils.SwapTransactionRequest;
import utils.Token;
import utils.VersionedTransaction;

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

    RequestBody body = RequestBody.create(MediaType.parse("application/json"),
        new Gson().toJson(swapTransactionRequest));

    Request request = new Request.Builder().url(JUPITER_TRANSACTION_API_URL).post(body).build();

    Response response = client.newCall(request).execute();
    SwapTransactionResponse swapTransactionResponse = null;

    try {
      swapTransactionResponse = new Gson().fromJson(response.body().string(), SwapTransactionResponse.class);
      return swapTransactionResponse;
    } catch (IOException e) {
      throw new IOException("Unable to retreive swap transaction.");
    }
  }
  
  
  public static void main(String [] args) throws Exception {
    String swapTransaction = "AQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAQAHDshryssvIknDPdR0scG1DNyP1xLKifEnJvhorNu0beobC0gHT3W46jBpye0/BrwyGXgq5M2OXJ03SYJWsZhLnVAfBZWufgnNXWbIY9Em5FuIGQsvnEpZGW4gXs7YoEWy6FjvZ3+1Y15kc3JLcOFrZAVUA06kehx7P82IhTxBXTJUezLU89nn17tMWRh3p/RdLPLoN4CAH3PX+xzbq3DXM7iJ4PJCWzKfF0zSSWDaBYDWlpqZtQYO3w8ZjExr0Lk8BpYFk9GEGFqu54yIFC9KUlPL5TROeFPmsulqVIRzRYr6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADBkZv5SEXMv/srbpyw5vnvIzlu8X3EmssQ5s6QAAAAAR51VvyMcBu7nTFbs5oFQf9sbLeo/SOUQKxzaJWvBOPBt324ddloZPZy+FGzut5rBy0he1fWzeROoz1hX7/AKk6uJA/tzXKscZ8Wa9IV+32Gwr4MqUKfFnjIZGeDsipvIyXJY9OJInxuz0QKRSODYMLWhOZ2v8QhASOe9jb6fhZtD/6J/XX9kp0wJsfKVh53ksJqzbfyd1RSzIap7OM5ejg9nYW3UHr81vVkzRpE95lzJdJh7NRfsZdjh9HhFHXfQgIAAUCwFwVAAgACQMEFwEAAAAAAAwGAAQAFAcKAQEHAgAEDAIAAAAA4fUFAAAAAAoBBAERDAYAAgAWBwoBAQkhCgsABAMBAhQWCQkNCRMOExEQAwEUFhITCwoKFRMFBg8JJMEgmzNB1pyBBAEAAAAmZAABAOH1BQAAAABqiRoBAAAAADIAAAoDBAAAAQkBeYEs0mE7HEwxSaxSebFx6rMMRR6cMyaZh48qg/nqv+UF9Pfx8voE9fjz9g==";
    byte [] swapTransactionBuf = BaseEncoding.base64().decode(swapTransaction);

    VersionedTransaction versionedTransaction = VersionedTransaction.deserialize(swapTransactionBuf);
    
    System.out.println(new Gson().toJson(versionedTransaction));

  }
  
}
