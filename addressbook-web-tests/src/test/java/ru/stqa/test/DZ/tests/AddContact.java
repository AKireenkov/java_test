package ru.stqa.test.DZ.tests;

import org.testng.annotations.Test;
import ru.stqa.test.DZ.model.ContactData;
import ru.stqa.test.DZ.model.Contacts;

import java.io.File;
import java.util.Comparator;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContact extends TestBase1 {

  @Test
  public void testAddContact() throws Exception {
    Contacts before = app1.contact().list();
    File photo= new File("src/test/resources/testPhoto.png");
    ContactData contact = new ContactData().withFirstname("UUU").withLastname("UUU").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withPhoto(photo).withGroup("test2");
    app1.contact().create(contact, true);
    assertThat(app1.contact().count(), equalTo(before.size() + 1));
    Contacts after = app1.contact().list();

    contact.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId()); //находим максимальный id(это id нового элемента)
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    assertThat(after, equalTo(before.withAdded(contact)));
  }

  @Test(enabled = false)
  public void testBadAddContact() throws Exception {    //проверка: если добаввляем контакт в некорректном формате, размер списка не меняется
    Contacts before = app1.contact().list();
    ContactData contact = new ContactData().withFirstname("zz'").withLastname("zz").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withGroup("test4");
    app1.contact().create(contact, true);
    assertThat(app1.contact().count(), equalTo(before.size()));
    Contacts after = app1.contact().list();

    contact.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    assertThat(after, equalTo(before));
  }

}
