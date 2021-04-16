package ru.stqa.test.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.test.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation(); //Делегирование в 2 этапа
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation(); //ApplicationManager дает доступ к помощнику по работе с группами
    app.getGroupHelper().returnToGroupPage();
  }

}
