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
import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = FilmixBackendApplication.class)
class RateControllerTest {
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
        String uri = "/rates";
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        // Then
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Rate[] rateList = mapFromJson(content, Rate[].class);
        assertTrue(rateList.length > 0);
    }

    @Test
    void getRateById() throws Exception {
        // Give
        String uri = "/rates/1";
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        // Then
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Rate rate = mapFromJson(content, Rate.class);
        assertEquals(rate.getId(), 1);
    }

    @Test
    void getRatesByRating() throws Exception {
        // Give
        int rateValue = 2;

        String uri = "/rates/rate/" + rateValue;
        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Rate[] rateList = mapFromJson(content, Rate[].class);

        // Then
        assertNotNull(rateList);

        for (Rate rate : rateList) {
            assertEquals(rateValue, rate.getRating());
        }
    }


    @Test
    void createRate() throws Exception {
        // Give
        RateDTO rateDTO = new RateDTO();
        rateDTO.setRating(4);

        String requestJson = mapToJson(rateDTO);

        String uri = "/rates";
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
        RateDTO createdRateDTO = mapFromJson(content, RateDTO.class);

        assertNotNull(createdRateDTO);
        assertEquals(rateDTO.getRating(), createdRateDTO.getRating());
    }


    @Test
    void updateRate() throws Exception {
        // Give
        long rateId = 1;
        RateDTO updatedRateDTO = new RateDTO();
        updatedRateDTO.setRating(3);

        String requestJson = mapToJson(updatedRateDTO);

        String uri = "/rates/" + rateId;
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
    void deleteRate() throws Exception {
        // Give
        int rateId = 2;

        String uri = "/rates/" + rateId;

        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // Then
        assertEquals(204, status);
    }
}
