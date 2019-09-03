package ru.bjcreslin.webex.repository.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class region70 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long innZakazchika;
    private Long idContact;
    private String url;
    private LocalDateTime deadline;
    private LocalDate contractDate;
    private LocalDate deliveryDate;
    private String deliveryAddress;
    private BigDecimal price;
}