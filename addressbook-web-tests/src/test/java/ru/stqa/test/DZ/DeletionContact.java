package ru.stqa.test.DZ;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionContact extends TestBase1 {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app1.contact().list().size() == 0) {//если нет контакта который можно редактировать -> создаем его
      app1.contact().create(new ContactData().withFirstname("aa").withLastname("aa").withAddress("test3").withPhoneH("test4").withPhoneM("test5").withPhoneW("test6").withEmail("test7").withGroup("test4"), true);
    }
    app1.contact().homePage();
  }

  @Test
  public void testDeletionContact() {
    Contacts before = app1.contact().list();
    String lastname = "zz";
    String firstname = "zz";
    app1.contact().delete(lastname, firstname);
    assertThat(app1.contact().count(), equalTo(before.size() - 1));//сравниваем размеры списков до/после
    Contacts after = app1.contact().list();

    assertThat(after, equalTo(before.without(app1.contact().lastnameid - 2)));   //сравниваем переменные
  }


}