package ru.bjcreslin.webex.repository.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Random;

/**
 * Контакты специалиста по закупкам
 */
@Builder
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

    /**
     * Случайное значение Contact для тестирования и т.п.
     * @return Contact
     */
    public static Contact getRandom() {
        Random random = new Random();
        return Contact.builder().id(random.nextLong()).
                firstName("first" + random.nextInt()).
                middleName("middle" + random.nextInt()).
                lastName("last" + random.nextInt()).
                email("no@mail.com" + random.nextInt()).
                comment("comment" + random.nextInt()).
                innOrganisation(random.nextLong()).
                phoneNumber("phone" + random.nextInt()).
                position("position" + random.nextInt()).
                build();
    }
}
