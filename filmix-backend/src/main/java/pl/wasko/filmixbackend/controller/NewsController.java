package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.CategoryNotFoundException;
import pl.wasko.filmixbackend.model.DTO.NewsDTO;
import pl.wasko.filmixbackend.service.NewsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        return newsService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NewsDTO newsDTO) throws InstantiationException, IllegalAccessException {
        NewsDTO savedNews = newsService.createNews(newsDTO);
        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            newsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }

    }
    @PostMapping("/add")
    public ResponseEntity<?> addNews(@RequestBody List<NewsDTO> newsesDTO) throws InstantiationException, IllegalAccessException {
        for (NewsDTO newsDTO : newsesDTO) {
            newsService.createNews(newsDTO);
        }
        return ResponseEntity.ok(newsesDTO);
    }
}
