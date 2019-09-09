package ru.bjcreslin.webex.repository.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Random;

/**
 * Контакты специалиста по закупкам
 */
//@Builder
@Entity
@Data
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;   //имя
    private String middleName;   //отчество
    private String lastName;  //Фамилия
    private Long innOrganisation;  // Инн организации
    private String position;   // должность
    private String email;  //е-почта
    private String phoneNumber;   // телефон
    private String comment;  //комментарий

    public Contact(String firstName, String middleName, String lastName, Long innOrganisation, String position, String email, String phoneNumber, String comment) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.innOrganisation = innOrganisation;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
    }

    public Contact() {
    }

    /**
     * Случайное значение Contact для тестирования и т.п.
     *
     * @return Contact
     */
    public static Contact getRandom() {
        Random random = new Random();
        return new Contact("first" + random.nextInt(),
                "middle" + random.nextInt(),
                "last" + random.nextInt(),
                random.nextLong(),
                "position" + random.nextInt(),
                "no@mail.com" + random.nextInt(),
                "phone" + random.nextInt(),
                "comment" + random.nextInt());
    }
}
