package pl.wasko.filmixbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.model.Opinion;


import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class OpinionControllerTest {
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getAll() throws Exception {
        // Give
        String uri = "/opinions";
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        // Then
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        Opinion[] opinionList = mapFromJson(content, Opinion[].class);
        assertTrue(opinionList.length>0);
    }

    @Test
    void getOpinionById() throws Exception {
        // Give
        String uri = "/opinions/2";
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        // Then
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Opinion opinion = mapFromJson(content, Opinion.class);
        assertEquals(2, opinion.getId());
    }

    @Test
    void getOpinionsByCreatedAt() throws Exception {
        // Give
        LocalDateTime createdAt = LocalDateTime.parse("2023-07-25T10:54:02");

        String uri = "/opinions/createdAt/" + createdAt;
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Opinion[] opinionList = mapFromJson(content, Opinion[].class);

        // Then
        assertNotNull(opinionList);

        for (Opinion opinion : opinionList) {
            assertEquals(createdAt, opinion.getCreatedAt());
        }
    }


    @Test
    void createOpinion() throws Exception {
        // Give
        OpinionDTO opinionDTO = new OpinionDTO();
        opinionDTO.setOpinion("Test opinion");

        String requestJson = mapToJson(opinionDTO);

        String uri = "/opinions";
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        // Then
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        OpinionDTO createdOpinionDTO = mapFromJson(content, OpinionDTO.class);

        assertNotNull(createdOpinionDTO);
        assertEquals(opinionDTO.getOpinion(), createdOpinionDTO.getOpinion());
    }


    @Test
    void updateTask() throws Exception {
        // Give
        long opinionId = 2L;
        OpinionDTO updatedOpinionDTO = new OpinionDTO();
        updatedOpinionDTO.setOpinion("Updated test opinion");

        String requestJson = mapToJson(updatedOpinionDTO);

        String uri = "/opinions/" + opinionId;
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        //Then
        assertEquals(204, status);

    }


    @Test
    void deleteOpinion() throws Exception {
        // Give
        long opinionId = 3L;

        String uri = "/opinions/" + opinionId;

        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Then
        assertEquals(204, status);
    }

}