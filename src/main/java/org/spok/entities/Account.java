package org.spok.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Size(min = 3, max = 12, message = "Login should be at least {min} characters long")
    @Column(length = 12, name = "acc_login")
    private String login;

    @NotEmpty
    @Size(min = 5, max = 60, message = "Password should be at least {min} characters long")
    @Column(length = 60)
    private String password;

    @NotEmpty
    @Column(length = 35)
    @Size(min = 5, max = 35, message = "Full name should be at least {min} characters long")
    private String fio;

    @NotNull
    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "account")
    private List<Contact> contacts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_login", nullable = false)
    private Set<Role> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
