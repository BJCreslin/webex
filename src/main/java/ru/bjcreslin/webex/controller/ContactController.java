package ru.bjcreslin.webex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.bjcreslin.webex.config.Constants;
import ru.bjcreslin.webex.exceptions.NotFoundException;
import ru.bjcreslin.webex.repository.domain.Contact;
import ru.bjcreslin.webex.service.ContactService;

import java.util.List;

@Controller
@RequestMapping(Constants.CONTACT_CONTROLLER_PREFIX)
public class ContactController {
    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Contact> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Contact read(@PathVariable long id) {
        Contact result = service.read(id);
        if (result == null) {
            throw new NotFoundException(Constants.CONTACT_OBJECT_NAME + id);
        }

        return result;
    }

}
