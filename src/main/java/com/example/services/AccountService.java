package com.example.services;


import com.example.dao.AccountRepository;
import com.example.dao.RoleRepository;
import com.example.entities.Account;
import com.example.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean save(Account account) {
        String login = account.getLogin();
        if (accountRepository.findByLogin(login) != null)
            return false;

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        account.setRoles(roles);
        account.setEnabled(true);

        accountRepository.save(account);
        return true;
    }
}
