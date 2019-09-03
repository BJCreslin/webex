package ru.bjcreslin.webex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.webex.repository.ContactRepository;
import ru.bjcreslin.webex.repository.domain.Contact;

import java.util.List;

@Service
public class ContactService {
    private ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact read(long id) {
        return repository.getOne(id);
    }
}
