package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class EditContact extends TestBase1 {
  @Test
  public void testEditContact(){
    app1.getContactHelper().editContact();
    app1.getContactHelper().fillContactForm(new ContactData("Andrey", "Petrov", "USA", "123123412345", "1000001", "1010101010", "TEST"));
    app1.getContactHelper().updateContact();
  }
}
