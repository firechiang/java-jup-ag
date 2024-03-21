package utils;

import java.util.ArrayList;

public class MessageAddressTableLookup {

  private String accountKey;
  private ArrayList<Integer> writableIndexes;
  private ArrayList<Integer> readonlyIndexes;
  
  
  public MessageAddressTableLookup(String accountKey, ArrayList<Integer> writableIndexes, ArrayList<Integer> readonlyIndexes) {
    this.setAccountKey(accountKey);
    this.setWritableIndexes(writableIndexes);
    this.setReadonlyIndexes(readonlyIndexes);
  }


  public String getAccountKey() {
    return accountKey;
  }


  public void setAccountKey(String accountKey) {
    this.accountKey = accountKey;
  }


  public ArrayList<Integer> getWritableIndexes() {
    return writableIndexes;
  }


  public void setWritableIndexes(ArrayList<Integer> writableIndexes) {
    this.writableIndexes = writableIndexes;
  }


  public ArrayList<Integer> getReadonlyIndexes() {
    return readonlyIndexes;
  }


  public void setReadonlyIndexes(ArrayList<Integer> readonlyIndexes) {
    this.readonlyIndexes = readonlyIndexes;
  }
  
}
