package ru.stqa.test.mantis.appmanager;

import ru.stqa.test.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class YandexHelper {

  private final ApplicationManager app;
  String user;
  String password;
  String host;

  public YandexHelper(ApplicationManager app) {
    this.app = app;
  }

  public static MailMessage toModelMail(Message m) {
    try {
      return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());// Получаем список адресов, берем первый адресс
      // содержимое письма переводим в строку, по полученным данным строим модельный объект

    } catch (MessagingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void initSession() throws MessagingException {
    user = app.getProperty("mail.user");
    password = app.getProperty("mail.password");
    host = app.getProperty("mail.host");
  }

  private Folder openInbox(String username, String pass) throws MessagingException {
    user = app.getProperty("mail.user");
    password = app.getProperty("mail.password");
    host = app.getProperty("mail.host");
    Properties prop = new Properties();
    prop.put("mail.store.protocol", "imaps");   //ssl

    Store store = Session.getInstance(prop).getStore();
    store.connect(host, user, password);

    Folder inbox = store.getFolder("INBOX");
    inbox.open(Folder.READ_WRITE);
    System.out.println(inbox.getMessageCount());
    return inbox;
  }

  public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
    long now = System.currentTimeMillis();
    while (System.currentTimeMillis() < now + timeout) {
      List<MailMessage> allMail = getAllMail(username, password);  //получаем всю почту
      if (allMail.size() > 0) { //если письма есть, возвращаем их
        return allMail;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password); //извлекаем объекты из почт. ящика
    List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  private void closeFolder(Folder inbox) throws MessagingException {
    inbox.close();
  }
}
