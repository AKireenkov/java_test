package ru.stqa.test.DZ;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase1 {

  @Test(enabled = false)
  public void testContactPhones() {   //сравнение контакта на форме редактирования и на главной странице
    app1.contact().homePage();
    int index = 1;
    ContactData contact = app1.contact().list().get(index - 1);
    ContactData contactInfoFromEditFrom = app1.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditFrom)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditFrom.getAddress()));
    assertThat(contact.getEmail(), equalTo(contactInfoFromEditFrom.getEmail()));
  }

  @Test
  public void testDetailsContactPhones() {   //сравнение контакта на форме Редактирования и Детали контакта
    app1.contact().homePage();
    int index = 4;    //номер контакта в таблице
    ContactData contact = app1.contact().list().get(index - 1);
    ContactData contactInfoFromDetailsFrom = app1.contact().infoFromDetailsFrom(contact);
    ContactData contactInfoFromEditFrom = app1.contact().infoFromEditForm(contact);

    assertThat(contactInfoFromEditFrom.getFirstname(), equalTo(contactInfoFromDetailsFrom.getFirstname()));
    assertThat(contactInfoFromEditFrom.getLastname(), equalTo(contactInfoFromDetailsFrom.getLastname()));
    assertThat(contactInfoFromEditFrom.getAddress(), equalTo(contactInfoFromDetailsFrom.getAddress()));
    assertThat(mergePhones(contactInfoFromEditFrom), equalTo(mergePhones(contactInfoFromDetailsFrom)));
    assertThat(contactInfoFromEditFrom.getEmail(), equalTo(contactInfoFromDetailsFrom.getEmail()));
  }

  public static String cleaned(String phone) {    //очистка от лишних символов
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("[HMW:]", "");
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneH(), contact.getPhoneM(), contact.getPhoneW())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }
}
