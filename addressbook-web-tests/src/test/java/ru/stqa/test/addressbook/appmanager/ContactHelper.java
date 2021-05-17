package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public int lastnameid = 2;
  public int firstnameid = 2;
  private Contacts contactCache = null;   //кеширование списка контактов

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAddContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneH());
    type(By.name("mobile"), contactData.getPhoneM());
    type(By.name("work"), contactData.getPhoneW());
    type(By.name("email"), contactData.getEmail());

    if (creation) {   //если true -> мы на форме создания
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());//выбираем группу из выпадающего списка
      }
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

  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();    //находим кол-во записей в списке, и выбираем по индексу
  }

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

  public void create(ContactData contact, boolean creation) {  //Создание контакта
    gotoAddContact();
    fillContactForm(contact, creation);
    submitAddContact();
    contactCache = null;
    homePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactCache = null;
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptDelete();
    contactCache = null;//сбрасываем кеш после изменения списка групп
    homePage();
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) { //Проверка на необходимость перехода на страницу
      return;
    }
    click(By.linkText("home"));
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);  //возвращаем копию кеша
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));  //элементы каждой строки
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));  //находим id элемента
      String lname = cells.get(1).getText();
      String fname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(fname).withLastname(lname).withAddress(address).withEmail(email).withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }     //вычисляем размер списка контактов по количеству чекбоксов на форме

  public ContactData infoFromEditForm(ContactData contact) {    //Получаем информацию на форме редактирования контакта
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).withEmail(email).withPhoneH(home).withPhoneM(mobile).withPhoneW(work);
  }

  private void editContactById(int id) {    //переходи на форму редактирования контакта по его id
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public ContactData infoFromDetailsFrom(ContactData contact) {  //Получаем информацию на форме Детали контакта
    JavascriptExecutor js = (JavascriptExecutor) wd;
    detailsContactById(contact.getId());
    String[] fs = wd.findElement(By.xpath("//*[@id=\"content\"]/b")).getText().split("\\s");
    String email = wd.findElement(By.xpath("//*[@id=\"content\"]/a")).getText();
    String address = (String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[1]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;");
    String home = (String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[3]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;");
    String mobile = (String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[3]/following::text()[2]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;");
    String work = (String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[3]/following::text()[3]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(fs[0]).withLastname(fs[1]).withAddress(address).withEmail(email).withPhoneH(home).withPhoneM(mobile).withPhoneW(work);
  }

  private void detailsContactById(int id) {   //переходи на форму детали контакта по его id
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }
}
