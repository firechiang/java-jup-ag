package utils;

public class SwapInfo {
  
  private String ammKey;
  private String label;
  private String inputMint;
  private String outputMint;
  private long inAmount;
  private long outAmount;
  private long feeAmount;
  private String feeMint;

  public String getAmmKey() {
    return ammKey;
  }

  public String getLabel() {
    return label;
  }

  public String getInputMint() {
    return inputMint;
  }

  public String getOutputMint() {
    return outputMint;
  }

  public long getInAmount() {
    return inAmount;
  }

  public long getOutAmount() {
    return outAmount;
  }

  public long getFeeAmount() {
    return feeAmount;
  }

  public String getFeeMint() {
    return feeMint;
  }

  public void setAmmKey(String ammKey) {
    this.ammKey = ammKey;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setInputMint(String inputMint) {
    this.inputMint = inputMint;
  }

  public void setOutputMint(String outputMint) {
    this.outputMint = outputMint;
  }

  public void setInAmount(long inAmount) {
    this.inAmount = inAmount;
  }

  public void setOutAmount(long outAmount) {
    this.outAmount = outAmount;
  }

  public void setFeeAmount(long feeAmount) {
    this.feeAmount = feeAmount;
  }

  public void setFeeMint(String feeMint) {
    this.feeMint = feeMint;
  }
}
