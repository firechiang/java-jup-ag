package utils;

public class SwapTransactionResponse {
  private String swapTransaction;
  private long lastValidBlockHeight;
  private long prioritizationFeeLamports;

  public long getLastValidBlockHeight() {
    return lastValidBlockHeight;
  }

  public void setLastValidBlockHeight(long lastValidBlockHeight) {
    this.lastValidBlockHeight = lastValidBlockHeight;
  }

  public long getPrioritizationFeeLamports() {
    return prioritizationFeeLamports;
  }

  public void setPrioritizationFeeLamports(long prioritizationFeeLamports) {
    this.prioritizationFeeLamports = prioritizationFeeLamports;
  }

  public String getSwapTransaction() {
    return swapTransaction;
  }

  public void setSwapTransaction(String swapTransaction) {
    this.swapTransaction = swapTransaction;
  }
}
