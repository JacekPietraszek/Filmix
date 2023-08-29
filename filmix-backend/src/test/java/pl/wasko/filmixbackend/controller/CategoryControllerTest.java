package pl.wasko.filmixbackend.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.wasko.filmixbackend.FilmixBackendApplication;
import pl.wasko.filmixbackend.model.Category;
import pl.wasko.filmixbackend.model.DTO.CategoryDTO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class CategoryControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }


    @Test
    void getAll() throws Exception {
        String uri = "/category";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Category[] category = mapFromJson(content, Category[].class);
        assertTrue(category.length > 0);

    }

    @Test
    void getCategoryById() throws Exception {
        String uri = "/category/2";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Category category = mapFromJson(content, Category.class);

        assertEquals(category.getId(), 2);

    }

    @Test
    void create() throws Exception {
        String uri = "/category";
        CategoryDTO categoryDTO = new CategoryDTO("Komedia", null, null);

        String inputJson = mapToJson(categoryDTO);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Category category = mapFromJson(content, Category.class);
        assertEquals(categoryDTO.getName(), category.getName());

    }

    @Test
    void deleteById() throws Exception {
        String uri = "/category/1";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);

    }
}