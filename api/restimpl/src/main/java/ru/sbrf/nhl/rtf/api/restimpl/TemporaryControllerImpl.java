package ru.sbrf.nhl.rtf.api.restimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.api.restapi.TemporaryController;
import ru.sbrf.nhl.rtf.core.model.Project;
import ru.sbrf.nhl.rtf.core.model.Template;
import ru.sbrf.nhl.rtf.core.model.TemplateField;
import ru.sbrf.nhl.rtf.core.model.component.ProjectStatus;
import ru.sbrf.nhl.rtf.core.model.component.TemplateFieldType;
import ru.sbrf.nhl.rtf.core.service.ProjectService;
import ru.sbrf.nhl.rtf.core.service.TemplateService;

import java.time.LocalDate;
import java.util.Arrays;

@RestController
public class TemporaryControllerImpl implements TemporaryController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ProjectService projectService;


    @Override
    public String initdb() {
//        projectService.getAll();
        createProject1();
        createProject2();
        return "Ok!";
    }

    private void createProject1() {
        Template t = new Template();
        t.setName("template1");
        TemplateField p1 = new TemplateField();
        p1.setName("par1");
        p1.getAddParams().put("min", "0");
        p1.getAddParams().put("max", "100");
        p1.setType(TemplateFieldType.NUMBER);
        TemplateField p2 = new TemplateField();
        p2.setName("par2");
        p2.setType(TemplateFieldType.STRING);

        TemplateField p3 = new TemplateField();
        p3.setName("par3");
        p3.setType(TemplateFieldType.STAR);
        p3.getAddParams().put("max", "5");

        t.setFields(Arrays.asList(new TemplateField[]{p1, p2, p3}));
        templateService.createTemplate(t);

        Project p = new Project();
        p.setName("project1");
        p.setDescription("some description");
        p.setStatus(ProjectStatus.NEW);
        p.setTemplate(t);
        p.setCreateDate(LocalDate.now());
        projectService.createProject(p);
    }

    private void createProject2() {
        Template t = new Template();
        t.setName("template2");
        TemplateField p1 = new TemplateField();
        p1.setName("par3");
        p1.setType(TemplateFieldType.STAR);
        TemplateField p2 = new TemplateField();
        p2.setName("par4");
        p2.setType(TemplateFieldType.BOOL);
        t.setFields(Arrays.asList(new TemplateField[]{p1, p2}));
        t.setFields(Arrays.asList(new TemplateField[]{p1}));
        templateService.createTemplate(t);

        Project p = new Project();
        p.setName("project2");
        p.setDescription("some description");
        p.setStatus(ProjectStatus.NEW);
        p.setTemplate(t);
        p.setCreateDate(LocalDate.now());
        projectService.createProject(p);
    }
}
