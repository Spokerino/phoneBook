package com.example.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id_contact", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idContact;

    @Size(min = 4, max = 30, message = "First name should be at least {min} characters long")
    @Column(name = "first_name", length = 30)
    private String firstName;

    @Size(min = 4, max = 30, message = "Last name should be at least {min} characters long")
    @Column(name = "last_name", length = 30)
    private String lastName;

    @Size(min = 4, max = 30, message = "Patronymic should be at least {min} characters long")
    @Column(length = 30)
    private String patronymic;

    @Size(min = 10, max = 15)
    @Column(name = "phone_mobile", length = 15, unique = true)
    private String phoneMobile;

    @Size(max = 15)
    @Column(name = "phone_home", length = 15)
    private String phoneHome;

    @Size(max = 100)
    @Column(length = 100)
    private String address;

    @Size(max = 25)
    @Column(length = 25)
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_login", nullable = false)
    private Account account;

    public Contact() {}

    public Contact(Account account, String firstName, String lastName, String patronymic, String phoneMobile) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneMobile = phoneMobile;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
