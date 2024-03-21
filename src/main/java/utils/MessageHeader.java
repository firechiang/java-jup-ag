package utils;

public class MessageHeader {
  private int numRequiredSignatures;
  private int numReadonlySignedAccounts;
  private int numReadonlyUnsignedAccounts;

  
  public MessageHeader(byte numRequiredSignatures, byte numReadonlySignedAccounts, byte numReadonlyUnsignedAccounts) {
    this.numRequiredSignatures = numRequiredSignatures;
    this.numReadonlySignedAccounts = numReadonlySignedAccounts;
    this.numReadonlyUnsignedAccounts = numReadonlyUnsignedAccounts;
  }
  
  public int getNumRequiredSignatures() {
    return numRequiredSignatures;
  }

  public void setNumRequiredSignatures(int numRequiredSignatures) {
    this.numRequiredSignatures = numRequiredSignatures;
  }

  public int getNumReadonlySignedAccounts() {
    return numReadonlySignedAccounts;
  }

  public void setNumReadonlySignedAccounts(int numReadonlySignedAccounts) {
    this.numReadonlySignedAccounts = numReadonlySignedAccounts;
  }

  public int getNumReadonlyUnsignedAccounts() {
    return numReadonlyUnsignedAccounts;
  }

  public void setNumReadonlyUnsignedAccounts(int numReadonlyUnsignedAccounts) {
    this.numReadonlyUnsignedAccounts = numReadonlyUnsignedAccounts;
  }

}
