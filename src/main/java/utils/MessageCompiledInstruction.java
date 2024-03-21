package utils;

import java.util.ArrayList;

public class MessageCompiledInstruction {
  
  private int programIdIndex;
  private ArrayList<Integer> accountKeyIndexes;
  private ArrayList<Integer> data;
  
  public MessageCompiledInstruction(int programIdIndex, ArrayList<Integer> accountKeyIndexes, ArrayList<Integer> data) {
    this.programIdIndex = programIdIndex;
    this.accountKeyIndexes = accountKeyIndexes;
    this.data = data;
  }

  public int getProgramIdIndex() {
    return programIdIndex;
  }

  public void setProgramIdIndex(int programIdIndex) {
    this.programIdIndex = programIdIndex;
  }

  public ArrayList<Integer> getAccountKeyIndexes() {
    return accountKeyIndexes;
  }

  public void setAccountKeyIndexes(ArrayList<Integer> accountKeyIndexes) {
    this.accountKeyIndexes = accountKeyIndexes;
  }

  public ArrayList<Integer> getData() {
    return data;
  }

  public void setData(ArrayList<Integer> data) {
    this.data = data;
  }

}
