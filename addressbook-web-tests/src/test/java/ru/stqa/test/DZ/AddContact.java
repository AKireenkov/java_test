package ru.stqa.test.DZ;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddContact extends TestBase1 {

  @Test
  public void testAddContact() throws Exception {
    List<ContactData> before = app1.getContactHelper().getContactList();
    ContactData contact = new ContactData("aa", "aa", "test3", "test4", "test5", "test6", "test7", "test4");
    app1.getContactHelper().createContact(contact, true);
    app1.returnHomePage();
    List<ContactData> after = app1.getContactHelper().getContactList();
    Assert.assertEquals(before.size() + 1, after.size());


    contact.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId()); //находим максимальный id(это id нового элемента)
    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
