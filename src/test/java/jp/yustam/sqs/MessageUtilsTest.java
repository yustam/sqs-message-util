package jp.yustam.sqs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import jp.yustam.sqs.pojo.DatabaseTask;
import jp.yustam.sqs.pojo.TableItem;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class MessageUtilsTest {

  static AmazonSQS sqs = new AmazonSQSClient();
  static String queueUrlDownloadTask;
  static String queueUrlDatabaseTask;

  String accessKey = "XXXXXXXXXXXXXXXXXXXX";
  String secretKey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  String regionName = "ap-northeast-1";
  String tableName = "test-dynamo-db-table";
  String host = "db.hogehoge.com";
  int port = 5432;
  String dbName = "hogehoge";

  @BeforeClass
  public static void setUpClass() throws IOException {
    Properties props = new Properties();
    props.load(ClassLoader.getSystemResourceAsStream("config.properties"));
    String queueDownloadTask = props.getProperty("aws.queue.download");
    String queueDatabaseTask = props.getProperty("aws.queue.database");
    queueUrlDownloadTask = sqs.createQueue(queueDownloadTask).getQueueUrl();
    queueUrlDatabaseTask = sqs.createQueue(queueDatabaseTask).getQueueUrl();
  }

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

    for (Iterator<DatabaseTask> itr = MessageUtils.getDatabaseTask().iterator(); itr.hasNext();) {
      DatabaseTask item = itr.next();
      assertThat(item.getAccessKey(), is(accessKey));
    }

  }

  @AfterClass
  public static void tearDownClass() {
    sqs.deleteQueue(queueUrlDownloadTask);
    sqs.deleteQueue(queueUrlDatabaseTask);
  }

}
