package ru.sbrf.nhl.rtf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesWebController {

    @GetMapping("/feedback")
    public String upload() {
        return "test";
    }

}
