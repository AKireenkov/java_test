package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData( "test5", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);   //Сравниваем размеры списков групп


   /* int max = 0;    //один из вариантов найти максимальное значение и передать его в setId
    for (GroupData g: after){   // находим максимальный идентификатор
      if (g.getId()>max){
        max = g.getId();
      }
    } */
    //Компаратор - интерфейс
//для компаратора создается анонимный класс
      //(o1, o2) -> Integer.compare(o1.getId(), o2.getId()) Сравниватель для 2х объектов
   // int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(); //находим максимальный объект через max(byId) и находим его id getId()
    /*
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); //превращаем список в поток,сравниваем объекты типа groupData, вычисляем макс, находим его id
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    */
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); // byId = ....   <- лямбда выражение
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
