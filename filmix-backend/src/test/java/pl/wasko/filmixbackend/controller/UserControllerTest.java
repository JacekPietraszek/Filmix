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
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class UserControllerTest {

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
    void addUser() throws Exception {
        String us = "/api/users";
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Kamil");
        userDTO.setPassword("Ślimak");

        String inputJson = mapToJson(userDTO);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(us)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);
        assertEquals(userDTO.getUsername(), user.getUsername());

    }

    @Test
    void getUserById() throws Exception {
        String us = "/api/users/11";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(us)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);

        assertEquals(user.getId(), 11L);

    }

    @Test
    void getAllUsers() throws Exception {
        String us = "/api/users";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(us)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User[] users = mapFromJson(content, User[].class);
        assertTrue(users.length > 0);

    }

    @Test
    void deleteById() throws Exception {
        String us = "/api/users/7/delete";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(us))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);

    }
}