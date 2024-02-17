package utils;

import java.util.ArrayList;

public class QuoteResponse {

  private String inputMint;
  private long inAmount;
  private String outputMint;
  private long outAmount;
  private long otherAmountThreshold;
  private String swapMode;
  private long slippageBps;
  private long platformFee = 0L;
  private long priceImpactPct;
  ArrayList<Route> routePlan = new ArrayList<Route>();
  private long contextSlot;
  private double timeTaken;

  public String getInputMint() {
    return inputMint;
  }

  public long getInAmount() {
    return inAmount;
  }

  public String getOutputMint() {
    return outputMint;
  }

  public long getOutAmount() {
    return outAmount;
  }

  public long getOtherAmountThreshold() {
    return otherAmountThreshold;
  }

  public String getSwapMode() {
    return swapMode;
  }

  public long getSlippageBps() {
    return slippageBps;
  }

  public long getPlatformFee() {
    return platformFee;
  }

  public long getPriceImpactPct() {
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

  public void setInAmount(long inAmount) {
    this.inAmount = inAmount;
  }

  public void setOutputMint(String outputMint) {
    this.outputMint = outputMint;
  }

  public void setOutAmount(long outAmount) {
    this.outAmount = outAmount;
  }

  public void setOtherAmountThreshold(long otherAmountThreshold) {
    this.otherAmountThreshold = otherAmountThreshold;
  }

  public void setSwapMode(String swapMode) {
    this.swapMode = swapMode;
  }

  public void setSlippageBps(long slippageBps) {
    this.slippageBps = slippageBps;
  }

  public void setPlatformFee(long platformFee) {
    this.platformFee = platformFee;
  }

  public void setPriceImpactPct(long priceImpactPct) {
    this.priceImpactPct = priceImpactPct;
  }

  public void setContextSlot(long contextSlot) {
    this.contextSlot = contextSlot;
  }

  public void setTimeTaken(long timeTaken) {
    this.timeTaken = timeTaken;
  }
}
