package org.spok.services;

import org.spok.dao.AccountRepository;
import org.spok.dao.ContactRepository;
import org.spok.entities.Account;
import org.spok.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Contact findOne(Integer id) {
        return contactRepository.findOne(id);
    }

    public List<Contact> findAllByAccountLogin(String login) {
        return contactRepository.findAllByAccountLogin(login);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public Account findAccountByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    public void delete(Integer id) {
        contactRepository.delete(id);
    }

/*    public void populate() {

        Account poma = new Account("poma", "poma", "Chloe O.O.");
        accountRepository.save(poma);
        contactRepository.save(new Contact(poma, "Chloe", "O'Brian", "Olegovich", "3332211"));
        contactRepository.save(new Contact(poma, "Kim", "Bauer", "Petrovich", "1112233"));
        contactRepository.save(new Contact(poma, "David", "Palmer", "Vasilievich", "4442233"));
        contactRepository.save(new Contact(poma, "Michelle", "Dessler", "Ostapovich", "1113355"));
    }*/

}
