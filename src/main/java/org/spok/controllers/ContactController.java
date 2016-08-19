package org.spok.controllers;


import org.spok.entities.Account;
import org.spok.entities.Contact;
import org.spok.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

        String login = getSecurityContextUser().getUsername();

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
        return contactService.findOne(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String editContact(@PathVariable Integer id) {
        contactService.delete(id);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editContact(Contact contact, BindingResult result) {
        return saveOrUpdate(contact, result);
    }

    private String saveOrUpdate(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contacts";
        }

        Account account = contactService.findAccountByLogin(getSecurityContextUser().getUsername());
        contact.setAccount(account);

        contactService.save(contact);
        return "redirect:/contacts";
    }

    private User getSecurityContextUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
