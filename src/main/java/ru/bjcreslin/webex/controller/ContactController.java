package ru.bjcreslin.webex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping(path = "",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getAll() {
        return service.getAll();
    }

    /**
     * Простой тест АПИ сервиса. Тупо выдает текст "Test Ok"
     * @return   текст "Test Ok"
     */
    @GetMapping(path = Constants.TEST_PREFIX, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody
    String simpleAPITest() {
        return "Test Ok";
    }


    @GetMapping(path = Constants.GET_PREFIX + "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Contact read(@PathVariable long id) {
        if (id == 0l) {
            return Contact.getRandom();
        }
        Contact result = service.read(id);
        if (result == null) {
            throw new NotFoundException("Get " + Constants.CONTACT_OBJECT_NAME + id);
        }
        return result;
    }

    @DeleteMapping(path = Constants.DELETE_PREFIX + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String delete(@PathVariable long id) {
        if (service.delete(id)) {
            return HttpStatus.OK.getReasonPhrase();
        } else {
            throw new NotFoundException("Delete " + Constants.CONTACT_OBJECT_NAME + id);
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
