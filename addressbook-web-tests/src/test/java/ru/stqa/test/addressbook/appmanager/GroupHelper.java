package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }


  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();    //находим кол-во записей в списке, и выбираем по индексу
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();    //находим кол-во записей в списке, и выбираем по индексу
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();

    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroups();
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  /*public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();    //создаем список типа GroupData
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));  //создаем список объектов. Находит все элементы тэг span и класс group
    for (WebElement element : elements) {   //проходим по списку elements
      String name = element.getText();    //проходим по списку elements и получаем их текст
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //Integer.parseInt преобразовывает строку в число
      groups.add(new GroupData().withId(id).withName(name));    //добавляем созданный объект в список
    }
    return groups;    //возвращаем список
  }
*/
  private Groups groupCache = null;

  public Groups all() {
    if (groupCache !=null){
      return new Groups((groupCache));
    }
    Groups groups = new Groups();    //создаем множества типа GroupData
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));  //создаем список объектов. Находит все элементы тэг span и класс group
    for (WebElement element : elements) {   //проходим по списку elements
      String name = element.getText();    //проходим по списку elements и получаем их текст
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //Integer.parseInt преобразовывает строку в число
      groups.add(new GroupData().withId(id).withName(name));    //добавляем созданный объект в список
    }
    return new Groups(groups);    //возвращаем список
  }


}
