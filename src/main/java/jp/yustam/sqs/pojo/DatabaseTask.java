package jp.yustam.sqs.pojo;

import java.util.List;

public class DatabaseTask {

  private String accessKey;
  private String secretKey;
  private String regionName;
  private String tableName;
  private String host;
  private int port;
  private String dbName;
  private List<TableItem> tables;

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public List<TableItem> getTables() {
    return tables;
  }

  public void setTables(List<TableItem> tables) {
    this.tables = tables;
  }

}
