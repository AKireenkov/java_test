package ru.stqa.test.DZ;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager1 {
  WebDriver wd;
  private SessionHelper1 sessionHelper1;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager1(String browser) {
    this.browser = browser;
  }

  protected void init() {
    if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    // wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    contactHelper = new ContactHelper(wd);
    sessionHelper1 = new SessionHelper1(wd);
    sessionHelper1.login("admin", "secret");
  }

  protected void stop() {
    wd.quit();
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}
