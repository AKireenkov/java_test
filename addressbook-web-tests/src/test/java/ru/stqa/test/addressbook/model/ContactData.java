package ru.stqa.test.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = 0;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String phoneH;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String phoneM;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String phoneW;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Transient  //Помечено пропускаемое поле
  private String allPhones;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @Expose
  @Transient
  private String group;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private final Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoneH(String phoneH) {
    this.phoneH = phoneH;
    return this;
  }

  public ContactData withPhoneM(String phoneM) {
    this.phoneM = phoneM;
    return this;
  }

  public ContactData withPhoneW(String phoneW) {
    this.phoneW = phoneW;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", phoneH='" + phoneH + '\'' +
            ", phoneM='" + phoneM + '\'' +
            ", phoneW='" + phoneW + '\'' +
            ", email='" + email + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", groups=" + groups +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (phoneH != null ? !phoneH.equals(that.phoneH) : that.phoneH != null) return false;
    if (phoneM != null ? !phoneM.equals(that.phoneM) : that.phoneM != null) return false;
    if (phoneW != null ? !phoneW.equals(that.phoneW) : that.phoneW != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
    return groups != null ? groups.equals(that.groups) : that.groups == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (phoneH != null ? phoneH.hashCode() : 0);
    result = 31 * result + (phoneM != null ? phoneM.hashCode() : 0);
    result = 31 * result + (phoneW != null ? phoneW.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (groups != null ? groups.hashCode() : 0);
    return result;
  }
}
