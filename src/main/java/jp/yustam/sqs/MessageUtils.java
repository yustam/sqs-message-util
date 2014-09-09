package jp.yustam.sqs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.yustam.sqs.mapper.JsonMapper;
import jp.yustam.sqs.pojo.DatabaseTask;
import jp.yustam.sqs.pojo.DownloadTask;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MessageUtils {

  private static AmazonSQS sqs;
  private static String queueUrlDownloadTask;
  private static String queueUrlDatabaseTask;
  private static int waitTimeSeconds;

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
