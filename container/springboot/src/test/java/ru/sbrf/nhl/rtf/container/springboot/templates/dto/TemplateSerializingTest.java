package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class TemplateSerializingTest {

    @Test
    public void testSerializer() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
        TemplateData data = new TemplateData();
        data.setName("New template 01");
        data.setFields(Arrays.asList(
                new TextField("Enter name"),
                new RangeField("Enter your age", 15, 45),
                new SelectField("Enter your sex", Arrays.asList(
                        new SelectField.Option("m", "Male"),
                        new SelectField.Option("f", "Female")
                ))
        ));

        String json = mapper.writeValueAsString(data);
        System.out.println(json);

        TemplateData repaired = mapper.readerFor(TemplateData.class).readValue(json);
        Assert.assertEquals(data, repaired);
    }

}