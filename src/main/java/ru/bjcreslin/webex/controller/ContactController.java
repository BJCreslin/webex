package ru.bjcreslin.webex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.webex.common_interfaces.APIController;
import ru.bjcreslin.webex.config.Constants;
import ru.bjcreslin.webex.exceptions.NotFoundException;
import ru.bjcreslin.webex.repository.domain.Contact;
import ru.bjcreslin.webex.service.ContactService;

import java.util.List;

@lombok.extern.java.Log
@Controller
@RequestMapping(Constants.CONTACT_CONTROLLER_PREFIX)
public class ContactController implements APIController {
    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }


    public @ResponseBody
    List<Contact> getAll() {
        List<Contact> contactList = service.getAll();
        log.info(contactList.toString());
        return contactList;
    }


    @GetMapping(path = Constants.TEST_PREFIX, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody
    String simpleAPITest() {
        return "Test Ok";
    }


    public @ResponseBody
    Contact getOne(@PathVariable long id) {
        if (id == 0L) {
            return Contact.getRandom();
        }
        Contact result = service.read(id);
        log.info(result.toString());
        if (result == null) {
            throw new NotFoundException("Get " + Constants.CONTACT_OBJECT_NAME + id);
        }
        return result;
    }


    public @ResponseBody
    String deleteOne(@PathVariable long id) {
        if (service.delete(id)) {
            return HttpStatus.OK.getReasonPhrase();
        } else {
            throw new NotFoundException("Delete " + Constants.CONTACT_OBJECT_NAME + id);
        }
    }

    @Override
    public String size() {
        long baseSize;
        if ((baseSize = service.size()) < 0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(baseSize);
            } catch (JsonProcessingException e) {
                throw new NotFoundException("Size to JSON convertion error " + baseSize);
            }
        } else {
            throw new NotFoundException("Количество меньше нуля " + baseSize);
        }
    }


    @PatchMapping(path = Constants.UPDATE_PREFIX,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String update(@RequestBody Contact contact) {
        if (service.update(contact)) {
            return HttpStatus.OK.getReasonPhrase();
        } else {
            throw new NotFoundException("Update " + Constants.CONTACT_OBJECT_NAME + contact);
        }
    }

    @PostMapping(path = Constants.CREATE_PREFIX,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Contact create(@RequestBody Contact contact) {
        Contact result = service.create(contact);
        return result;
    }


}
