package ru.stqa.test.DZ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends Base {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAddContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    category(By.name("firstname"), contactData.getFirstname());
    category(By.name("lastname"), contactData.getLastname());
    category(By.name("address"), contactData.getAddress());
    category(By.name("home"), contactData.getPhoneH());
    category(By.name("mobile"), contactData.getPhoneM());
    category(By.name("work"), contactData.getPhoneW());
    category(By.name("email"), contactData.getEmail());

    if (creation) {   //если true -> мы на форме создания
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //выбираем группу из выпадающего списка
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group"))); //на форме редакитования выбора группы быть не должно
    }
  }

  public void gotoAddContact() {
    if (isElementPresent(By.tagName("h1"))  //Проверка на необходимость перехода на страницу
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("photo"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContact() {
    click(By.id("70"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDelete() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {    //возвращает значение кнопки edit для контакта, на главной странице
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void createContact(ContactData contact, boolean creation) {  //Создание контакта
    gotoAddContact();
    fillContactForm(contact, creation);
    submitAddContact();
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) { //Проверка на необходимость перехода на страницу
      return;
    }
    click(By.linkText("home page"));
  }
}
