package utils;

public class SwapTransactionRequest {
  private QuoteResponse quoteResponse;
  private String userPublicKey;
  
  public String getUserPublicKey() {
    return userPublicKey;
  }
  public void setUserPublicKey(String userPublicKey) {
    this.userPublicKey = userPublicKey;
  }
  
  public QuoteResponse getQuoteResponse() {
    return quoteResponse;
  }
  
  public void setQuoteResponse(QuoteResponse quoteResponse) {
    this.quoteResponse = quoteResponse;
  }

}
