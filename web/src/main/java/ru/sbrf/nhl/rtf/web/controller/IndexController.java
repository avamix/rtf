package ru.sbrf.nhl.rtf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Контроллер главной страницы сайта
 */
@Controller
public class IndexController {

    /**
     * Метод для выборки всех рецептов, либо с фильтром по названию
     *
     * @param model объект выводимой модели
     * @return строка - название представления модели
     */
    @RequestMapping(value = "/", method = GET)
    public String index(ModelMap model) {
        return "index";
    }
}
