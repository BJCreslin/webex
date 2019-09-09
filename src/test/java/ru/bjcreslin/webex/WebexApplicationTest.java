package ru.bjcreslin.webex;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import ru.bjcreslin.webex.config.Constants;
import ru.bjcreslin.webex.controller.ContactController;
import ru.bjcreslin.webex.repository.domain.Contact;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WebexApplicationTest {
    @Autowired
    private ContactController controller;
    @Autowired
    private MockMvc mockMvc;

    private String localAdress = "http://localhost:8080";

    @Test
    public void controllerExist() throws Exception {
        assertThat(controller).isNotNull();
    }


    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get(Constants.CONTACT_CONTROLLER_PREFIX + "/0")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(containsString("middleName")));
    }

    @Test
    public void newAndGetAndDel() throws Exception {
        Contact contact = Contact.getRandom();
        ObjectMapper mapper = new ObjectMapper();
        String contactJSON = mapper.writeValueAsString(contact);
        this.mockMvc.perform(post(Constants.CONTACT_CONTROLLER_PREFIX + Constants.CREATE_PREFIX).
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(contactJSON).
                requestAttr("Contact", contact)).
                andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    public void newAndGet() throws Exception {
        Contact contact = Contact.getRandom();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Contact> request = new HttpEntity<>(contact);
        Contact
                contactReceive = restTemplate.postForObject(localAdress + Constants.CONTACT_CONTROLLER_PREFIX + Constants.CREATE_PREFIX, request,
                Contact.class);
        Assert.assertEquals(contact.getFirstName(), contactReceive.getFirstName());
    }

}
