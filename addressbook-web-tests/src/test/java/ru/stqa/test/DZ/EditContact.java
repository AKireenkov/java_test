package ru.stqa.test.DZ;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditContact extends TestBase1 {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app1.contact().list().size() == 0) {//если нет контакта который можно редактировать -> создаем его
      app1.contact().create(new ContactData()
              .withFirstname("aa").withLastname("aa").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withGroup("test4"), true);
    }
    app1.contact().homePage();
  }

  @Test
  public void testEditContact() {
    Contacts before = app1.contact().list();
    int index = 4;    //номер контакта в таблице
    ContactData contact = new ContactData()
            .withId(before.get(index - 1).getId()).withFirstname("TEST").withLastname("TEST").withAddress("RUSSIA").withPhoneH("123123412345").withPhoneM("1000001").withPhoneW("1010101010").withEmail("TEST");
    app1.contact().modify(index, contact);
    assertThat(app1.contact().count(), equalTo(before.size()));
    Contacts after = app1.contact().list();

    before.remove(index - 1);
    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(after, before);
    //assertThat(after, equalTo(before.without(index-1).withAdded(contact)));
  }
}
