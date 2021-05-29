package ru.stqa.test.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.test.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
  private final Wiser wiser;
  private final ApplicationManager app;

  public MailHelper(ApplicationManager app) {
    this.app = app;
    wiser = new Wiser();  //инициализирует почтовый сервер Wiser
  }

  public static MailMessage toModelMail(WiserMessage m) {
    try {
      MimeMessage mm = m.getMimeMessage();  //берем объект
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());//берем список получателей и оставляем только первый
    } catch (MessagingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<MailMessage> waitForMail(int count, long timeout) {
    long start = System.currentTimeMillis();  //присваиваем текущее время
    while (System.currentTimeMillis() < start + timeout) {  //если время ожидания не истекло
      if (wiser.getMessages().size() >= count) {  //если пришло достаточное количество почты
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList()); //каждый элемент превращаем в модельный через toModelMail()
      }
      //иначе ждем, и снова проверяем нужное количество писем
      try {
        Thread.sleep(1000); //подождать 1000 мсек
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  public void start() {
  }

  public void stop() {
  }
}
