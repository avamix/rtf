package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.util.Arrays.asList;

public class TemplateSerializingTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }

    @Test
    public void testTemplateSerializer() throws IOException {
        TemplateData data = new TemplateData();
        data.setName("New template 01");
        data.setFields(asList(
                new TextField("name", "Enter name"),
                new RangeField("age", "Enter your age", 15, 45),
                new SelectField("sex", "Enter your sex", asList(
                        new SelectField.Option("m", "Male"),
                        new SelectField.Option("f", "Female")
                ))
        ));

        String json = mapper.writeValueAsString(data);
        System.out.println(json);

        TemplateData repaired = mapper.readerFor(TemplateData.class).readValue(json);
        Assert.assertEquals(data, repaired);
    }

    @Test
    public void testFilledTemplateSerializer() throws IOException {
        FilledTemplateData data = new FilledTemplateData();
        data.setTemplateId("SOME_RANDOM_UUID_AS_STRING");
        data.setFields(asList(
                new TextField.Value("name", "Team NHL"),
                new RangeField.Value("age", 33),
                new SelectField.Value("sex", "m")
        ));

        String json = mapper.writeValueAsString(data);
        System.out.println(json);

        FilledTemplateData repaired = mapper.readerFor(FilledTemplateData.class).readValue(json);
        Assert.assertEquals(data, repaired);
    }

}