package ru.stqa.test.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.test.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  // @BeforeMethod //вкл для встроенного почтового сервера
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("akireenkovtest+%s@yandex.ru", now);
    //app.yandex().createUser(user, password);
    app.registration().start(user, email);
    app.yandex().initSession();
    // List<MailMessage> mailMessages = app.mail().waitForMail(2, 100000);
    List<MailMessage> mailMessages = app.yandex().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  // @AfterMethod(alwaysRun = true)   //вкл для встроенного почтового сервера
  public void stopMailServer() {
    app.mail().stop();
  }
}
