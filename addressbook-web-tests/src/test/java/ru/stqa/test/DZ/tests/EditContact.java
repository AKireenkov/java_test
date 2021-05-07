package ru.stqa.test.DZ.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.DZ.model.ContactData;
import ru.stqa.test.DZ.model.Contacts;
import java.util.Comparator;
import static org.hamcrest.CoreMatchers.equalTo;

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
    int index = 6;    //номер контакта в таблице
    ContactData contact = new ContactData()
            .withId(before.get(index - 1).getId()).withFirstname("TEST1").withLastname("TEST1").withAddress("RUSSIA").withPhoneH("123123412345").withPhoneM("1000001").withPhoneW("1010101010").withEmail("TEST");
    app1.contact().modify(index, contact);
    MatcherAssert.assertThat(app1.contact().count(), equalTo(before.size()));
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
