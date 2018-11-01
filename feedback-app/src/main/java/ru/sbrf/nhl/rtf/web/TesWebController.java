package ru.sbrf.nhl.rtf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sbrf.nhl.rtf.service.SnapshotService;

@Controller
public class TesWebController {
    private final SnapshotService service;

    @Autowired
    public TesWebController(SnapshotService service) {
        this.service = service;
    }

    @GetMapping("/feedback")
    public String upload() {
        service.createSnapshots();
        return "test";
    }

}
