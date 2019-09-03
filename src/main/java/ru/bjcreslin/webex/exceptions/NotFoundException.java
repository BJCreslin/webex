package ru.bjcreslin.webex.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private String text;

    public NotFoundException(String text) {
        this.text = text;
        log.warning("Запрос на несуществуюший объект- " + text);
    }
}
