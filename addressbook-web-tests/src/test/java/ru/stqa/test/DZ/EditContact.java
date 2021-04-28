package ru.stqa.test.DZ;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class EditContact extends TestBase1 {
  @Test
  public void testEditContact() {
    if (!app1.getContactHelper().isThereAContact()) {//если нет контакта который можно редактировать -> создаем его
      app1.getContactHelper().createContact(new ContactData("test10", "test211", "test3", "test4", "test5", "test6", "test7", "test8"), true);
    }
    app1.getContactHelper().gotoHomePage();
    List<ContactData> before = app1.getContactHelper().getContactList();
    app1.getContactHelper().editContact(4);
    ContactData contact = new ContactData(before.get(3).getId(), "cc", "cc", "USA", "123123412345", "1000001", "1010101010", "TEST", null);
    app1.getContactHelper().fillContactForm(contact, false);
    app1.getContactHelper().updateContact();
    app1.getContactHelper().gotoHomePage();
    List<ContactData> after = app1.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size());

    before.remove(3); //номер на1 меньше чем индекс, по которому редактируем
    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }
}
