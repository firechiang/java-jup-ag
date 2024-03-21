package utils;

public class AccountMeta {
  private String publicKey;
  private boolean isSigner;
  private boolean isWritable;

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public boolean isSigner() {
    return isSigner;
  }

  public void setSigner(boolean isSigner) {
    this.isSigner = isSigner;
  }

  public boolean isWritable() {
    return isWritable;
  }

  public void setWritable(boolean isWritable) {
    this.isWritable = isWritable;
  }
}
