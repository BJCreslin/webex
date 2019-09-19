package ru.bjcreslin.webex.common_interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.webex.config.Constants;
import ru.bjcreslin.webex.repository.domain.Contact;

import java.util.List;

public interface APIController {

    /***
     * выдает все данные из базы
     *
     * @return Лист с данными
     */
    @GetMapping(path = Constants.GET_ALL_PREFIX,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    List<Contact> getAll();

    /**
     * Простой тест АПИ сервиса. Тупо выдает текст "Test Ok"
     *
     * @return текст "Test Ok"
     */
    @GetMapping(path = Constants.TEST_PREFIX, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    String simpleAPITest();

    /**
     * выдает одно значение по id
     *
     * @param id -id запрашиваемого объекта (Long)
     * @return возвращает значение
     * Если нет возможности прочитать, то инициализирует исключение NotFoundException
     */
    @GetMapping(path = Constants.GET_PREFIX + "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Contact getOne(@PathVariable long id);

    /**
     * Удаляет один элемент по id
     *
     * @param id -id удаляемого объекта
     * @return Значение Http.status
     * Если нет возможности удалить, то инициализирует исключение NotFoundException
     */
    @DeleteMapping(path = Constants.DELETE_PREFIX + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    String deleteOne(@PathVariable long id);

    /**
     * Возращает количество данных в базе
     */
    @GetMapping(path = Constants.SIZE_PREFIX)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    String size();

    /**
     * Обновить данные
     *
     * @param contact КОнтакт для обновления
     * @return HttpStatus
     */
    @PatchMapping(path = Constants.UPDATE_PREFIX,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    String updateOne(@RequestBody Contact contact);

    /**
     * Создание нового контакта
     *
     * @param contact
     * @return новый Contact
     */
    @PostMapping(path = Constants.CREATE_PREFIX,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Contact createOne(@RequestBody Contact contact);

}
