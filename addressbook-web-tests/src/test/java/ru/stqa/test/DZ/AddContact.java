package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class AddContact extends TestBase1 {

  @Test
  public void testAddContact() throws Exception {
    app1.getContactHelper().createContact(new ContactData("test2", "test1", "test3", "test4", "test5", "test6", "test7", "test4"), true);
    app1.returnHomePage();
  }

}
