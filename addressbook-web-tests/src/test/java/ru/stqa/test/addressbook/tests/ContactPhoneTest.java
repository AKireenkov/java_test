package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  public static String cleaned(String phone) {    //очистка от лишних символов
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("[HMW:]", "");
  }

  @Test(enabled = false)
  public void testContactPhones() {   //сравнение контакта на форме редактирования и на главной странице
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditFrom)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditFrom.getAddress()));
    assertThat(contact.getEmail(), equalTo(contactInfoFromEditFrom.getEmail()));
  }

  @Test
  public void testDetailsContactPhones() {   //сравнение контакта на форме Редактирования и Детали контакта
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsFrom = app.contact().infoFromDetailsFrom(contact);
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

    assertThat("Firstname doesn't match", contactInfoFromEditFrom.getFirstname(), equalTo(contactInfoFromDetailsFrom.getFirstname()));
    assertThat("Lastname doesn't match", contactInfoFromEditFrom.getLastname(), equalTo(contactInfoFromDetailsFrom.getLastname()));
    assertThat("Address doesn't match", contactInfoFromEditFrom.getAddress(), equalTo(contactInfoFromDetailsFrom.getAddress()));
    assertThat("Phones doesn't match", mergePhones(contactInfoFromEditFrom), equalTo(mergePhones(contactInfoFromDetailsFrom)));
    assertThat("Email doesn't match", contactInfoFromEditFrom.getEmail(), equalTo(contactInfoFromDetailsFrom.getEmail()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneH(), contact.getPhoneM(), contact.getPhoneW())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }
}
