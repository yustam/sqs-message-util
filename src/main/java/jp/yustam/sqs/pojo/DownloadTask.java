package jp.yustam.sqs.pojo;

import java.util.List;

public class DownloadTask {

  private String accessKey;
  private String secretKey;
  private String bucket;
  private String host;
  private List<FileItem> files;

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

  public String getBucket() {
    return bucket;
  }

  public void setBucket(String bucket) {
    this.bucket = bucket;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public List<FileItem> getFiles() {
    return files;
  }

  public void setFiles(List<FileItem> files) {
    this.files = files;
  }

}
