package org.spok;


import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spok.controllers.AccountController;
import org.spok.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = PhoneBookApplication.class)
public class AccountControllerTest {

    private MockMvc mockMvc;

    private Account account;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();

        account = new Account();
        account.setLogin("testAccount");
        account.setPassword("testAccountPassword");
        account.setFio("testAccountFullName");
    }

    @Test
    public void getLoginPage() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @Transactional //prevents saving test data to database
    public void postRegistrationForm() throws Exception {



        Gson gson = new Gson();
        String json = gson.toJson(account);


        MvcResult result = mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        Assert.assertEquals("Response should be {'msg':'Registration was completed successfully!'",
                "{\"msg\":\"Registration was completed successfully!\"}", contentAsString);
    }


    @Test
    @WithMockUser
    public void getContacts() throws Exception{

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "You have no contacts yet. Add some!"));
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public AccountController accountController() {
            return new AccountController();
        }
    }
}
