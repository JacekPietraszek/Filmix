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
import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.Role;
import pl.wasko.filmixbackend.model.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class RoleControllerTest {

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
    void addRole() throws Exception {
        String us = "/api/roles";
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("admin");


        String inputJson = mapToJson(roleDTO);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(us)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Role role = mapFromJson(content, Role.class);
        assertEquals(roleDTO.getName(), roleDTO.getName());
    }

    @Test
    void getRoleById() throws Exception {
        String us = "/api/roles/16";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(us)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Role role = mapFromJson(content, Role.class);

        assertEquals(role.getId(), 16L);

    }

    @Test
    void getAllRoles() throws Exception {
        String us = "/api/roles";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(us)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Role[] roles = mapFromJson(content, Role[].class);
        assertTrue(roles.length > 0);
    }

    @Test
    void deleteById() throws Exception {
        String us = "/api/users/3/delete";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(us))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);

    }
}