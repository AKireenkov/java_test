package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddingContactToGroup extends TestBase {

  private ContactData targetContact;
  private GroupData targetGroup;

  @BeforeClass
  private void chooseContactAndGroup() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (//если группа с таким названием повторяется хотя бы дважды, группа не подходит
                groups.stream().filter(g -> g.getName().equals(group.getName())).collect(Collectors.toList()).size() > 1) {
          continue;
        }
        //если в такую группу контакт не входит, выбираем эту пару
        if (!contact.getGroups().contains(group)) {
          targetContact = contact;
          targetGroup = group;
          return;
        }
      }
    }

    //создаем новый контакт и группу
    targetContact = new ContactData().withFirstname("Ivan").withLastname("Andreevich");
    targetGroup = new GroupData().withName(String.format("Group %s", System.currentTimeMillis()));
    app.goTo().homePage();
    app.contact().create(targetContact, true);
    app.goTo().groupPage();
    app.group().create(targetGroup);

    //запрашиваем новые списки, с уже добавленными элементами
    contacts = app.db().contacts();
    groups = app.db().groups();

    // находим максимальные id, это и будут id новых элементов
    targetContact.withId(contacts.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    targetGroup.withId(groups.stream().mapToInt((g) -> g.getId()).max().getAsInt());

  }

  //добавление контакта в группу
  @Test
  public void testContactAddToGroup() {
    app.goTo().homePage();
    app.contact().selectContactById(targetContact.getId());
    app.contact().addContactToGroup(targetGroup);
    app.goTo().homePage();
    ContactData contactFromDb = app.db().getContacts(targetContact.getId());
    assertTrue(contactFromDb.getGroups().contains(targetGroup));
  }

  //удаление контакта из группы
  @Test
  public void testContactDeleteFromGroup() {
    app.goTo().homePage();
    app.contact().selectGroupFilter(targetGroup);
    app.contact().selectContactById(targetContact.getId());
    app.contact().submitContactDeleteFromGroup();
    app.goTo().homePage();
    ContactData contactFromDb = app.db().getContacts(targetContact.getId());
    assertFalse(contactFromDb.getGroups().contains(targetGroup));

  }
}
