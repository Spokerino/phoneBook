package org.spok.services;


import org.spok.dao.AccountRepository;
import org.spok.entities.Account;
import org.spok.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account) {
        String login = account.getLogin();
        if (accountRepository.findByLogin(login) != null)
            return null;

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        account.setRoles(roles);
        account.setEnabled(true);

        String hashedPassword = new BCryptPasswordEncoder().encode(account.getPassword());
        account.setPassword(hashedPassword);

        accountRepository.save(account);
        return account;
    }
}
