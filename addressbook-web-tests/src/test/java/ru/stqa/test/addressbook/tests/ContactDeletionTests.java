package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {//если нет контакта который можно редактировать -> создаем его
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().withFirstname("aa").withLastname("aa").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").inGroup(groups.iterator().next()), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testDeletionContact() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));//сравниваем размеры списков до/после
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));   //сравниваем переменные
  }
}