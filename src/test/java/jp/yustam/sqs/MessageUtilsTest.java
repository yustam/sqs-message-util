package jp.yustam.sqs;

import java.util.Arrays;

import jp.yustam.sqs.pojo.DatabaseTask;
import jp.yustam.sqs.pojo.TableItem;

import org.junit.Test;

public class MessageUtilsTest {

  String accessKey = "XXXXXXXXXXXXXXXXXXXX";
  String secretKey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  String regionName = "ap-northeast-1";
  String tableName = "test-dynamo-db-table";
  String host = "db.hogehoge.com";
  int port = 5432;
  String dbName = "hogehoge";

  @Test
  public void test() {
    DatabaseTask task = new DatabaseTask();
    task.setAccessKey(accessKey);
    task.setSecretKey(secretKey);
    task.setRegionName(regionName);
    task.setTableName(tableName);
    task.setHost(host);
    task.setPort(port);
    task.setDbName(dbName);
    task.setTables(Arrays.asList(new TableItem("", ""), new TableItem("", ""),
        new TableItem("", "")));

    MessageUtils.sendDatabaseTask(task);
  }

}
