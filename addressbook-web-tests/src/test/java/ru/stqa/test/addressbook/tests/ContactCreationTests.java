package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddContact() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/testPhoto.png");
    ContactData contact = new ContactData().withFirstname("XXXXX").withLastname("XXXXXX").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withPhoto(photo).withGroup("test2");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadAddContact() throws Exception {    //проверка: если добаввляем контакт в некорректном формате, размер списка не меняется
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("zz'").withLastname("zz").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withGroup("test4");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
