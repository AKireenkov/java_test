package ru.stqa.test.DZ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Base {
  protected WebDriver wd;

  public Base(WebDriver wd) {
    this.wd = wd;
  }
  protected void click(By position) {
    wd.findElement(position).click();
  }

  protected void category(By position, String texts) {
    click(position);
    wd.findElement(position).clear();
    wd.findElement(position).sendKeys(texts);
  }
}
  /* private boolean isElementPresent(By by) {    //Лишнее
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  } */
