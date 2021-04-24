package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class EditContact extends TestBase1 {
  @Test
  public void testEditContact() {
    if (!app1.getContactHelper().isThereAContact()) {//если нет контакта который можно редактировать -> создаем его
      app1.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"), true);
    }
    app1.getContactHelper().gotoHomePage();
    app1.getContactHelper().editContact();
    app1.getContactHelper().fillContactForm(new ContactData("Andrey", "Kireenkov", "USA", "123123412345", "1000001", "1010101010", "TEST", null), false);
    app1.getContactHelper().updateContact();
  }
}
