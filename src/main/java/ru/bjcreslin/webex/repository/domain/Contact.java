package ru.bjcreslin.webex.repository.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Контакты специалиста по закупкам
 */
@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long innOrganisation;
    private String position;
    private String email;
    private String phoneNumber;
    private String comment;
}
