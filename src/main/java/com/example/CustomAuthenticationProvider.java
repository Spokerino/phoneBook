package com.example;

import com.example.dao.AccountRepository;
import com.example.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Account account = accountRepository.findByLogin(authentication.getName());

        if (account == null) {
            throw new BadCredentialsException("No such account!");
        }

        String userLogin = account.getLogin();
        String userPassword = account.getPassword();

        if (!authentication.getCredentials().toString().equals(userPassword)) {
            throw new BadCredentialsException("Incorrect password!");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRoles().iterator().next().getRole()));

        return new UsernamePasswordAuthenticationToken(userLogin, userPassword, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
