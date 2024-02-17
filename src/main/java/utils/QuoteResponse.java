package utils;

import java.util.ArrayList;

public class QuoteResponse {

  private String inputMint;
  private String inAmount;
  private String outputMint;
  private String outAmount;
  private String otherAmountThreshold;
  private String swapMode;
  private long slippageBps;
  private PlatformFee platformFee;
  private double priceImpactPct;
  ArrayList<Route> routePlan = new ArrayList<Route>();
  private long contextSlot;
  private double timeTaken;

  public String getInputMint() {
    return inputMint;
  }

  public String getInAmount() {
    return inAmount;
  }

  public String getOutputMint() {
    return outputMint;
  }

  public String getOutAmount() {
    return outAmount;
  }

  public String getOtherAmountThreshold() {
    return otherAmountThreshold;
  }

  public String getSwapMode() {
    return swapMode;
  }

  public long getSlippageBps() {
    return slippageBps;
  }

  public PlatformFee getPlatformFee() {
    return platformFee;
  }

  public double getPriceImpactPct() {
    return priceImpactPct;
  }
  
  public ArrayList<Route> getRoutePlan() {
    return routePlan;
  }

  public long getContextSlot() {
    return contextSlot;
  }

  public double getTimeTaken() {
    return timeTaken;
  }


  public void setInputMint(String inputMint) {
    this.inputMint = inputMint;
  }

  public void setInAmount(String inAmount) {
    this.inAmount = inAmount;
  }

  public void setOutputMint(String outputMint) {
    this.outputMint = outputMint;
  }

  public void setOutAmount(String outAmount) {
    this.outAmount = outAmount;
  }

  public void setOtherAmountThreshold(String otherAmountThreshold) {
    this.otherAmountThreshold = otherAmountThreshold;
  }

  public void setSwapMode(String swapMode) {
    this.swapMode = swapMode;
  }

  public void setSlippageBps(long slippageBps) {
    this.slippageBps = slippageBps;
  }

  public void setPlatformFee(PlatformFee platformFee) {
    this.platformFee = platformFee;
  }

  public void setPriceImpactPct(double priceImpactPct) {
    this.priceImpactPct = priceImpactPct;
  }

  public void setContextSlot(long contextSlot) {
    this.contextSlot = contextSlot;
  }

  public void setTimeTaken(long timeTaken) {
    this.timeTaken = timeTaken;
  }
}
