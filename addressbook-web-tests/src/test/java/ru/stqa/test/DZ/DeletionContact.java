package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class DeletionContact extends TestBase1 {
  @Test
  public void testDeletionContact() {
    if (!app1.getContactHelper().isThereAContact()) { //если нет контакта который можно удалить -> создаем его
      app1.getContactHelper().createContact(new ContactData("test1", "Petrov", "USA", "123123412345", "1000001", "1010101010", "TEST", "test4"), true);
    }
    app1.getContactHelper().gotoHomePage();
    app1.getContactHelper().selectContact();
    app1.getContactHelper().deleteContact();
    app1.getContactHelper().acceptDelete();
  }
}
