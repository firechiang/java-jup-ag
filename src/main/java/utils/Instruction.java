package utils;

import java.util.ArrayList;

public class Instruction {
  private String programId;
  private ArrayList<AccountMeta> accounts = new ArrayList<>();
  private String data;

  public String getProgramId() {
    return programId;
  }

  public void setProgramId(String programId) {
    this.programId = programId;
  }

  public ArrayList<AccountMeta> getAccounts() {
    return accounts;
  }

  public void setAccounts(ArrayList<AccountMeta> accounts) {
    this.accounts = accounts;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
