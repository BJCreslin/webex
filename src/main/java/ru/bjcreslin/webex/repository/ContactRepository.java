package ru.bjcreslin.webex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bjcreslin.webex.repository.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
