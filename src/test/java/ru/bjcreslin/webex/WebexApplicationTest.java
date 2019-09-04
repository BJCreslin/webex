package ru.bjcreslin.webex;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bjcreslin.webex.config.Constants;
import ru.bjcreslin.webex.controller.ContactController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebexApplicationTest {
    @Autowired
    private ContactController controller;
    @Autowired
    private MockMvc mockMvc;

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

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme