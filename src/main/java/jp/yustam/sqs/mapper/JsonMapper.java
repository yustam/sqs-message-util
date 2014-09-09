package jp.yustam.sqs.mapper;

import jp.yustam.sqs.pojo.DatabaseTask;
import jp.yustam.sqs.pojo.DownloadTask;

import com.google.gson.Gson;

public class JsonMapper {

  public static String toString(DatabaseTask task) {
    Gson gson = new Gson();
    return gson.toJson(task);
  }

  public static DatabaseTask parseDatabaseTask(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, DatabaseTask.class);
  }

  public static String toString(DownloadTask task) {
    Gson gson = new Gson();
    return gson.toJson(task);
  }

  public static DownloadTask parseDownloadTask(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, DownloadTask.class);
  }

}
