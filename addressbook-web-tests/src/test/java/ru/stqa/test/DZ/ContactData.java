package ru.stqa.test.DZ;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String phoneH;
  private final String phoneM;
  private final String phoneW;
  private final String email;

  public ContactData(String firstname, String lastname, String address, String phoneH, String phoneM, String phoneW, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phoneH = phoneH;
    this.phoneM = phoneM;
    this.phoneW = phoneW;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneH() {
    return phoneH;
  }

  public String getPhoneM() {
    return phoneM;
  }

  public String getPhoneW() {
    return phoneW;
  }

  public String getEmail() {
    return email;
  }
}
