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
                new TextFieldDto("name", "Enter name"),
                new RangeFieldDto("age", "Enter your age", 15, 45),
                new SelectFieldDto("sex", "Enter your sex", asList(
                        new SelectFieldDto.Option("m", "Male"),
                        new SelectFieldDto.Option("f", "Female")
                ))
        ));

        String json = mapper.writeValueAsString(data);
        System.out.println(json);

        TemplateData repaired = mapper.readerFor(TemplateData.class).readValue(json);
        Assert.assertEquals(data, repaired);

        KnownObject<TemplateData, Integer> createdObject = new KnownObject<>(7712, 0, repaired);
        json = mapper.writeValueAsString(createdObject);
        System.out.println(json);

    }

    @Test
    public void testFilledTemplateSerializer() throws IOException {
        FilledTemplateData data = new FilledTemplateData();
        data.setTemplateId(7712L);
        data.setFields(asList(
                new TextFieldDto.Value("name", "Team NHL"),
                new RangeFieldDto.Value("age", 33),
                new SelectFieldDto.Value("sex", "m")
        ));

        String json = mapper.writeValueAsString(data);
        System.out.println(json);

        FilledTemplateData repaired = mapper.readerFor(FilledTemplateData.class).readValue(json);
        Assert.assertEquals(data, repaired);
    }

}