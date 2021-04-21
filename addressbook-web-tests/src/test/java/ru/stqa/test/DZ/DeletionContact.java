package ru.stqa.test.DZ;

import org.testng.annotations.Test;

public class DeletionContact extends TestBase1 {
  @Test
  public void testDeletionContact() {
    app1.getContactHelper().selectContact();
    app1.getContactHelper().deleteContact();
    app1.getContactHelper().acceptDelete();
  }
}
