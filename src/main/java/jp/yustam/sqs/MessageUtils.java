package jp.yustam.sqs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import jp.yustam.sqs.mapper.JsonMapper;
import jp.yustam.sqs.pojo.DatabaseTask;
import jp.yustam.sqs.pojo.DownloadTask;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MessageUtils {

  private static AmazonSQS sqs = new AmazonSQSClient();
  private static String queueUrlDownloadTask;
  private static String queueUrlDatabaseTask;
  private static int waitTimeSeconds;

  static {
    Properties props = new Properties();
    try {
      props.load(ClassLoader.getSystemResourceAsStream("config.properties"));
      queueUrlDownloadTask = sqs.getQueueUrl(props.getProperty("aws.queue.download")).getQueueUrl();
      queueUrlDatabaseTask = sqs.getQueueUrl(props.getProperty("aws.queue.database")).getQueueUrl();
      waitTimeSeconds = Integer.parseInt(props.getProperty("aws.queue.waittime"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void sendDownloadTask(DownloadTask task) {
    String messageBody = JsonMapper.toString(task);
    sqs.sendMessage(new SendMessageRequest(queueUrlDownloadTask, messageBody));
  }

  public static void sendDatabaseTask(DatabaseTask task) {
    String messageBody = JsonMapper.toString(task);
    sqs.sendMessage(new SendMessageRequest(queueUrlDatabaseTask, messageBody));
  }

  public static List<DownloadTask> getDownloadTask() {
    List<DownloadTask> tasks = new ArrayList<>();
    List<Message> messages = getMessage(queueUrlDownloadTask);
    for (Iterator<Message> itr = messages.iterator(); itr.hasNext();) {
      tasks.add(JsonMapper.parseDownloadTask(itr.next().getBody()));
    }
    return tasks;
  }

  public static List<DatabaseTask> getDatabaseTask() {
    List<DatabaseTask> tasks = new ArrayList<>();
    List<Message> messages = getMessage(queueUrlDatabaseTask);
    for (Iterator<Message> itr = messages.iterator(); itr.hasNext();) {
      tasks.add(JsonMapper.parseDatabaseTask(itr.next().getBody()));
    }
    return tasks;
  }

  private static List<Message> getMessage(String queueUrl) {
    return sqs.receiveMessage(
        new ReceiveMessageRequest(queueUrl).withWaitTimeSeconds(waitTimeSeconds)).getMessages();
  }

}
