package ru.stqa.test.DZ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager1 {
  WebDriver wd;
  private SessionHelper1 sessionHelper1;
  private ContactHelper contactHelper;

  protected void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    contactHelper = new ContactHelper(wd);
    sessionHelper1 = new SessionHelper1(wd);
    sessionHelper1.login("admin", "secret");
  }


  protected void returnHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  protected void stop() {
    wd.quit();
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
