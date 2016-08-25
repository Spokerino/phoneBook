package org.spok.services;

import org.spok.dao.AccountRepository;
import org.spok.dao.ContactRepository;
import org.spok.entities.Account;
import org.spok.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

}
