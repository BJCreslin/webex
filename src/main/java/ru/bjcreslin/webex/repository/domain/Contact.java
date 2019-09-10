package ru.bjcreslin.webex.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

/**
 * Контакты специалиста по закупкам
 */
//@Builder
@Entity
@Getter
@Setter
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;   //имя
    @Column
    private String middleName;   //отчество
    @Column
    private String lastName;  //Фамилия
    @Column
    private Long innOrganisation;  // Инн организации
    @Column
    private String position;   // должность
    @Column
    private String email;  //е-почта
    @Column
    private String phoneNumber;   // телефон
    @Column
    private String comment;  //комментарий

    //    public Contact(String firstName, String middleName, String lastName, Long innOrganisation, String position, String email, String phoneNumber, String comment) {
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.innOrganisation = innOrganisation;
//        this.position = position;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.comment = comment;
//    }


    /**
     * Случайное значение Contact для тестирования и т.п.
     *
     * @return Contact
     */
    public static Contact getRandom() {
        Random random = new Random();
        return new Contact(null,"first" + random.nextInt(),
                "middle" + random.nextInt(),
                "last" + random.nextInt(),
                random.nextLong(),
                "position" + random.nextInt(),
                "no@mail.com" + random.nextInt(),
                "phone" + random.nextInt(),
                "comment" + random.nextInt());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", innOrganisation=" + innOrganisation +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
