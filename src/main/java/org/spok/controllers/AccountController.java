package org.spok.controllers;

import org.spok.entities.Account;
import org.spok.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String openLoginForm(Account account) {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveFromRegistrationTab(@RequestBody @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "Error!";
        }

        if (accountService.save(account) == null) {
            return "exist";
        }

        return "{\"msg\":\"Registration was completed successfully!\"}";
    }

}
