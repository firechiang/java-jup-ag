package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class VersionedTransaction {
  private static final int SIGNATURE_LENGTH_IN_BYTES = 64;
  private ArrayList<byte[]> signatures = new ArrayList<>();
  private VersionedMessage message;

  public VersionedTransaction(ArrayList<byte[]> signatures, VersionedMessage message) {
    this.signatures = signatures;
    this.message = message;
  }

  private String recentBlockHash;

  public String getRecentBlockHash() {
    return recentBlockHash;
  }

  public void setRecentBlockHash(String recentBlockHash) {
    this.recentBlockHash = recentBlockHash;
  }

  public ArrayList<byte[]> getSignatures() {
    return signatures;
  }

  public void setSignatures(ArrayList<byte[]> signatures) {
    this.signatures = signatures;
  }

  public VersionedMessage getMessage() {
    return message;
  }

  public void setMessage(VersionedMessage message) {
    this.message = message;
  }

  public static VersionedTransaction deserialize(byte[] swapTransactionBuf) throws Exception {
    ArrayList<byte[]> signatures = new ArrayList<>();
    byte[] transactionBytes = Arrays.copyOf(swapTransactionBuf, swapTransactionBuf.length);
    int signaturesLength = ShortvecEncoding.decodeLength(transactionBytes);

    transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

    for (int i = 0; i < signaturesLength; i++) {
      signatures.add(Arrays.copyOfRange(transactionBytes, 0, SIGNATURE_LENGTH_IN_BYTES));
      transactionBytes = Arrays.copyOfRange(transactionBytes, SIGNATURE_LENGTH_IN_BYTES, transactionBytes.length);
    }

    VersionedMessage message = VersionedMessage.deserialize(transactionBytes);

    return new VersionedTransaction(signatures, message);
  }

}
