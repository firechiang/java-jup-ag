package jupiter;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import utils.Token;

public class JupiterAG {

	private OkHttpClient client;
	private String JUPITER_URL = "https://token.jup.ag/";

	public JupiterAG() {
		this.client = new OkHttpClient();
	}

	public Token[] getTokens(boolean strict) throws IOException {
		Token[] tokens;

		Request request = new Request.Builder().url(strict ? JUPITER_URL + "strict" : JUPITER_URL + "all").build();
		Response response = client.newCall(request).execute();

		tokens = new Gson().fromJson(response.body().string(), Token[].class);

		return tokens;
	}

}
