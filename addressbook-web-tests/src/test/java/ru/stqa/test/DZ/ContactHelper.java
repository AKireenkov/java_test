package ru.stqa.test.DZ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends Base {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  protected void submitAddContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  protected void fillContactForm(ContactData contactData) {
    category(By.name("firstname"), contactData.getFirstname());
    category(By.name("lastname"), contactData.getLastname());
    category(By.name("address"), contactData.getAddress());
    category(By.name("home"), contactData.getPhoneH());
    category(By.name("mobile"), contactData.getPhoneM());
    category(By.name("work"), contactData.getPhoneW());
    category(By.name("email"), contactData.getEmail());
  }

  protected void gotoAddContact() {
    click(By.linkText("add new"));
  }

  protected void editContact(){
    click(By.xpath("//img[@alt='Edit']"));
  }
  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }
  protected void selectContact(){
    click(By.id("13"));
  }
  protected void deleteContact(){
    click(By.xpath("//input[@value='Delete']"));
  }
  public void acceptDelete() {
   wd.switchTo().alert().accept();
  }
}
