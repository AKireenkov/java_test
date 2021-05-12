package ru.stqa.test.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

  private final Set<GroupData> delegate; //берем множество из существующего объекта delegate

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);  //создаем новое множество с теми же элементами
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
