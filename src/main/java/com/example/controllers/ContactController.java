package com.example.controllers;


import com.example.entities.Account;
import com.example.entities.Contact;
import com.example.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;



    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getContactList(Contact contact){

        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("contacts");
        List<Contact> contacts = /*(List<Contact>)*/ contactService.findAllByAccountLogin(login);

        if (contacts.size() > 0) {
            mav.addObject("contacts", contacts);
        }
        else {
            mav.addObject("message", "You have no contacts yet. Add some!");
        }

        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addContact(Contact contact, BindingResult result) {
        return saveOrUpdate(contact, result);
    }

    @ResponseBody
    @RequestMapping(value = "/loadContact/{id}", method = RequestMethod.GET)
    public Contact getContactByAjax(@PathVariable Integer id) {
        Contact contact = contactService.findOne(id);
        contact.setAccount(null);
        return contact;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editContact(Contact contact, BindingResult result) {
        return saveOrUpdate(contact, result);
    }

    private String saveOrUpdate(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contacts";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = contactService.findAccountByLogin((String) auth.getPrincipal());
        contact.setAccount(account);


        contactService.save(contact);
        return "redirect:/contacts";
    }

}
