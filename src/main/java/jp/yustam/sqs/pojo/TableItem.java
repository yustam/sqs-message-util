package jp.yustam.sqs.pojo;

public class TableItem {

  private String tableName;
  private String fileName;

  public TableItem() {

  }

  public TableItem(String tableName, String fileName) {
    this.tableName = tableName;
    this.fileName = fileName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}
