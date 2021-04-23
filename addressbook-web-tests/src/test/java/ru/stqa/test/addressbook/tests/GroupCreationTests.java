package ru.stqa.test.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.test.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test4", null, null));
  }

}
