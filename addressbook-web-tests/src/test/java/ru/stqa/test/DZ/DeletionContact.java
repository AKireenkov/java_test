package ru.stqa.test.DZ;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class DeletionContact extends TestBase1 {
  @Test
  public void testDeletionContact() {
    if (!app1.getContactHelper().isThereAContact()) { //если нет контакта который можно удалить -> создаем его
      app1.getContactHelper().createContact(new ContactData("test1", "Petrov", "USA", "123123412345", "1000001", "1010101010", "TEST", "test4"), true);
    }
    List<ContactData> before = app1.getContactHelper().getContactList();
    app1.getContactHelper().gotoHomePage();
    app1.getContactHelper().selectContact("yy", "yy");
    app1.getContactHelper().deleteContact();
    app1.getContactHelper().acceptDelete();
    app1.getContactHelper().gotoHomePage();
    List<ContactData> after = app1.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);   //сравниваем размеры списков до/после

    before.remove(app1.getContactHelper().getLastnameid() - 2);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);   //сравниваем переменные
  }
}