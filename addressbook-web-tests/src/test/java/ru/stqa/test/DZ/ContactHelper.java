package ru.stqa.test.DZ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index - 1).click();
  }

  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }


  public int getLastnameid() {
    return lastnameid;
  }

  public int getFirstnameid() {
    return firstnameid;
  }

  public int lastnameid = 2;
  public int firstnameid = 2;

  public void selectContact(String lastname, String firstname) {
    String checkbox = "//*[@id=\"maintable\"]/tbody/tr[%d]/td[1]"; //Столбец с чекбоксом

    List<WebElement> elementslastname = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[2]"));//столбец фамилия
    for (WebElement element : elementslastname) {
      String name = element.getText();
      if (!name.equals(lastname)) {
        lastnameid += 1;
      } else {
        break;
      }
    }
    List<WebElement> elementsfirstname = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[3]"));//столбец имя
    for (WebElement element : elementsfirstname) {
      String name = element.getText();
      if (!name.equals(firstname)) {
        firstnameid += 1;
      } else {
        break;
      }
    }
    if (lastnameid == firstnameid) {
      click(By.xpath(String.format(checkbox, lastnameid)));
    }
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
    click(By.linkText("home"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elementslastname = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[2]")); //столбец Фамилия
    List<WebElement> elementsfirstname = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[3]"));  //столбец имя
    List<WebElement> entrys = wd.findElements(By.name("entry"));  //элементы каждой строки
    for (int i = 0; i < elementsfirstname.size(); i++) {
      String lname = elementslastname.get(i).getText();
      String fname = elementsfirstname.get(i).getText();
      WebElement entry = entrys.get(i);
      int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));  //находим id элемента
      ContactData contact = new ContactData(id, fname, lname, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
