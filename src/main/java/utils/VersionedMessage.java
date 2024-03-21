package utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.bitcoinj.core.Base58;
import org.p2p.solanaj.core.PublicKey;

public class VersionedMessage {
  private static final int VERSION_PREFIX_MASK = 0x7F;
  private static final int PUBLIC_KEY_LENGTH = 32;
  private MessageHeader messageHeader;
  private ArrayList<PublicKey> staticAccountKeys;
  private String recentBlockhash;
  private ArrayList<MessageCompiledInstruction> compiledInstructions;
  private ArrayList<MessageAddressTableLookup> addressTableLookups;

  public VersionedMessage(MessageHeader messageHeader, ArrayList<PublicKey> staticAccountKeys, String recentBlockhash,
      ArrayList<MessageCompiledInstruction> compiledInstructions,
      ArrayList<MessageAddressTableLookup> addressTableLookups) {
    this.messageHeader = messageHeader;
    this.staticAccountKeys = staticAccountKeys;
    this.recentBlockhash = recentBlockhash;
    this.compiledInstructions = compiledInstructions;
    this.setAddressTableLookups(addressTableLookups);
  }

  public MessageHeader getMessageHeader() {
    return messageHeader;
  }

  public void setMessageHeader(MessageHeader messageHeader) {
    this.messageHeader = messageHeader;
  }

  public ArrayList<PublicKey> getStaticAccountKeys() {
    return staticAccountKeys;
  }

  public void setStaticAccountKeys(ArrayList<PublicKey> staticAccountKeys) {
    this.staticAccountKeys = staticAccountKeys;
  }

  public String getRecentBlockhash() {
    return recentBlockhash;
  }

  public void setRecentBlockhash(String recentBlockhash) {
    this.recentBlockhash = recentBlockhash;
  }

  public ArrayList<MessageCompiledInstruction> getCompiledInstructions() {
    return compiledInstructions;
  }

  public void setCompiledInstructions(ArrayList<MessageCompiledInstruction> compiledInstructions) {
    this.compiledInstructions = compiledInstructions;
  }

  public static VersionedMessage deserialize(byte[] transactionBytes) throws Exception {
    int prefix = (transactionBytes[0] & 0xFF);

    int maskedPrefix = prefix & VERSION_PREFIX_MASK;
    int version = maskedPrefix;

    if (prefix == maskedPrefix)
      throw new Exception("Expected versioned message but received legacy message.");
    if (version != 0)
      throw new Exception("Expected versioned message with version 0 but found version " + version);

    ArrayList<PublicKey> staticAccountKeys = new ArrayList<>();

    MessageHeader messageHeader = new MessageHeader(transactionBytes[1], transactionBytes[2], transactionBytes[3]);

    transactionBytes = Arrays.copyOfRange(transactionBytes, 4, transactionBytes.length);

    int staticAccountKeysLength = ShortvecEncoding.decodeLength(transactionBytes);

    transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

    for (int i = 0; i < staticAccountKeysLength; i++) {
      staticAccountKeys.add(new PublicKey(Arrays.copyOfRange(transactionBytes, 0, PUBLIC_KEY_LENGTH)));
      transactionBytes = Arrays.copyOfRange(transactionBytes, PUBLIC_KEY_LENGTH, transactionBytes.length);
    }

    String recentBlockhash = Base58.encode(Arrays.copyOfRange(transactionBytes, 0, PUBLIC_KEY_LENGTH));
    transactionBytes = Arrays.copyOfRange(transactionBytes, PUBLIC_KEY_LENGTH, transactionBytes.length);

    int compiledInstructionCount = ShortvecEncoding.decodeLength(transactionBytes);
    transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

    ArrayList<MessageCompiledInstruction> compiledInstructions = new ArrayList<>();

    for (int i = 0; i < compiledInstructionCount; i++) {
      int programIdIndex = transactionBytes[0];
      transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

      int accountKeyIndexesLength = ShortvecEncoding.decodeLength(transactionBytes);
      transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

      ArrayList<Integer> accountKeyIndex = new ArrayList<>();

      for (int j = 0; j < accountKeyIndexesLength; j++) {
        accountKeyIndex.add((int)transactionBytes[j]);
      }
      transactionBytes = Arrays.copyOfRange(transactionBytes, accountKeyIndexesLength, transactionBytes.length);

      int dataLength = ShortvecEncoding.decodeLength(transactionBytes);
      transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

      ArrayList<Integer> data = new ArrayList<>();
      for (int k = 0; k < dataLength; k++) {
        data.add((transactionBytes[k] & 0xFF));
      }

      transactionBytes = Arrays.copyOfRange(transactionBytes, dataLength, transactionBytes.length);
     
      compiledInstructions.add(new MessageCompiledInstruction(programIdIndex, accountKeyIndex, data));

    }

    ArrayList<MessageAddressTableLookup> addressTableLookup = new ArrayList<>();
    int addressTableLookupsCount = ShortvecEncoding.decodeLength(transactionBytes);
    transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

    for (int i = 0; i < addressTableLookupsCount; i++) {
      String accountKey = Base58.encode(Arrays.copyOfRange(transactionBytes, 0, PUBLIC_KEY_LENGTH));
      transactionBytes = Arrays.copyOfRange(transactionBytes, PUBLIC_KEY_LENGTH, transactionBytes.length);

      int writableIndexesLength = ShortvecEncoding.decodeLength(transactionBytes);
      transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

      ArrayList<Integer> writableIndexes = new ArrayList<>();
      for (int j = 0; j < writableIndexesLength; j++) {
        writableIndexes.add((transactionBytes[j] & 0xFF));
      }
      transactionBytes = Arrays.copyOfRange(transactionBytes, writableIndexesLength, transactionBytes.length);

      int readonlyIndexesLength = ShortvecEncoding.decodeLength(transactionBytes);
      transactionBytes = Arrays.copyOfRange(transactionBytes, 1, transactionBytes.length);

      ArrayList<Integer> readonlyIndexes = new ArrayList<>();
      for (int j = 0; j < readonlyIndexesLength; j++) {
        readonlyIndexes.add((transactionBytes[j] & 0xFF));
      }
      transactionBytes = Arrays.copyOfRange(transactionBytes, readonlyIndexesLength, transactionBytes.length);

      addressTableLookup.add(new MessageAddressTableLookup(accountKey, writableIndexes, readonlyIndexes));
    }

    return new VersionedMessage(messageHeader, staticAccountKeys, recentBlockhash, compiledInstructions,
        addressTableLookup);
  }

  public ArrayList<MessageAddressTableLookup> getAddressTableLookups() {
    return addressTableLookups;
  }

  public void setAddressTableLookups(ArrayList<MessageAddressTableLookup> addressTableLookups) {
    this.addressTableLookups = addressTableLookups;
  }

}
