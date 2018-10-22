package ru.sbrf.nhl.rtf.api.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO только для тестирования, наполняет базу парочкой проектов
public interface TemporaryController {

    @RequestMapping(value = "/initdb", method = RequestMethod.GET)
    String initdb();

}
