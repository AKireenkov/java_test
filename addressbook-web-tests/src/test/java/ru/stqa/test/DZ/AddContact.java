package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class AddContact extends TestBase1 {

  @Test
  public void testAddContact() throws Exception {
    app1.getContactHelper().gotoAddContact();
    app1.getContactHelper().fillContactForm(new ContactData("Petr", "Nikolaev", "Russia", "111111111", "222222222", "333333333", "Qwertrty12345"));
    app1.getContactHelper().submitAddContact();
    app1.returnHomePage();
  }

}
