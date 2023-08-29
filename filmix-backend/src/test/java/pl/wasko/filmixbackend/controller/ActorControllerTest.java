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
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class ActorControllerTest {

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
        String uri = "/actors";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Actor[] actors = mapFromJson(content, Actor[].class);
        assertTrue(actors.length > 0);
    }

    @Test
    void getById() throws Exception {
        String uri = "/actors/2";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Actor actor = mapFromJson(content, Actor.class);

        assertEquals(actor.getId(), 2L);
    }

    @Test
    void create() throws Exception {
        String uri = "/actors";
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setFirstName("Joanna");
        actorDTO.setLastName("Kowal");
        actorDTO.setBirthdate(Date.valueOf("2023-04-04"));

        String inputJson = mapToJson(actorDTO);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Actor actor = mapFromJson(content, Actor.class);
        assertEquals(actorDTO.getFirstName(), actor.getFirstName());
    }

    @Test
    void deleteById() throws Exception {
        String uri = "/actors/1";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}