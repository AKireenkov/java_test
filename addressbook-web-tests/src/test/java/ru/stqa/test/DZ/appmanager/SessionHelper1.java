package ru.stqa.test.DZ.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper1 extends Base {

  public SessionHelper1(WebDriver wd) {
    super(wd);
  }

  public void login(String login, String password) {
    category(By.name("user"), login);
    category(By.name("pass"), password);
    click(By.xpath("//input[@value='Login']"));
  }

}
