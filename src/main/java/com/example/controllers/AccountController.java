package com.example.controllers;

import com.example.entities.Account;
import com.example.services.AccountService;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLoginForm(Account account) {
        return "login";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout() {
//        ModelAndView mav = new ModelAndView("login");
//        mav.addObject("msg", "You have been logged out.");
//
//        return mav;
//    }

    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveFromRegistrationTab(@RequestBody @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "Nope";
        }

        if (!accountService.save(account)) {
            return "exist";
        }

        return "{\"msg\":\"Registration was completed successfully!\"}";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String openRegistrationForm(Account account) {
        return "registrationForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegistrationForm(@Valid Account account,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "registrationForm";
        }

        accountService.save(account);
        return "redirect:/login?registered";
    }

}
