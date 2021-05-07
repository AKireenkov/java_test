package ru.stqa.test.DZ;

import com.google.common.collect.ForwardingList;
import java.util.ArrayList;
import java.util.List;

public class Contacts extends ForwardingList<ContactData> {

  private List<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new ArrayList<>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new ArrayList<>();
  }

  @Override
  protected List<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(int contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
