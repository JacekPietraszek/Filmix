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
import pl.wasko.filmixbackend.model.DTO.MovieDTO;
import pl.wasko.filmixbackend.model.Movie;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FilmixBackendApplication.class)
class MovieControllerTest {

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

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getAll() throws Exception {
        String uri = "/movies";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Movie[] movies = mapFromJson(content, Movie[].class);
        assertTrue(movies.length > 0);
    }

    @Test
    void getById() throws Exception {
        String uri = "/movies/1";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Movie movie = mapFromJson(content, Movie.class);

        assertEquals(movie.getId(), 1);

    }

    @Test
    void createMovie() throws Exception {
        String uri = "/movies";
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle("Oppenheimer");
        movieDTO.setReleaseDate(LocalDate.parse("2023-07-26"));
        movieDTO.setDescription("Fajny");
        movieDTO.setEpisodes(0);

        String inputJson = mapToJson(movieDTO);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Movie movie = mapFromJson(content, Movie.class);
        assertEquals(movieDTO.getTitle(), movie.getTitle());
    }

    @Test
    void deleteById() throws Exception {
        String uri = "/movies/1";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);

    }
    @Test
    void putCategoryIntoMovie() throws Exception {
        // Arrange
        long movieId = 2L;
        long categoryId = 2L;
        String uri = "/movies/" + movieId + "/category/" + categoryId;

        // Act
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri))
                .andReturn();

        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}