package ru.stqa.test.DZ.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

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
    if (texts != null) {  //возможность передавать дефолтные значения в поле
      String existingText = wd.findElement(position).getAttribute("value");
      if (!texts.equals(existingText)) {    //если передаваемый текст не совпадает с уже существующим
        wd.findElement(position).clear();
        wd.findElement(position).sendKeys(texts);
      }
    }
  }

  protected void attach(By position, File file) {
    if (file != null) {  //возможность передавать дефолтные значения в поле
        wd.findElement(position).sendKeys(file.getAbsolutePath());
    }
  }

  protected boolean isElementPresent(By position) {
    try {
      wd.findElement(position);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}

