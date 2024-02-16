package jupiter;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import utils.Token;

public class JupiterAG {

  private OkHttpClient client;
  private final String JUPITER_URL = "https://token.jup.ag/";
  private final String JUPITER_PRICE_API_URL = "https://price.jup.ag/v4/price";

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
   * Returns the unit price of the token based on the buy amount of 1 unit in USDC.
   * 
   * @param token - contract-address for token
   * @return price in USDC
   * @throws IOException 
   */
  public double getTokenPrice(String token) throws IOException {
    if (token == null || token.length() == 0)
      throw new IllegalArgumentException("Token cannot be null or an empty string.");
      
    Request request = new Request.Builder().url(JUPITER_PRICE_API_URL + "?ids=" + token).build();
    Response response = client.newCall(request).execute();
    
    JSONObject json = new JSONObject(response.body().string());
    
    try {
      return json.getJSONObject("data").getJSONObject(token).getDouble("price");
    } catch (JSONException e) {
      throw new JSONException("Unable to determine price.");
    }
    
  }
  
}
