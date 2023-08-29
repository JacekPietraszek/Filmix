package pl.wasko.filmixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.wasko.filmixbackend.model.Image;
import pl.wasko.filmixbackend.repository.ImageRepository;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping
    Long uploadImage(@RequestParam String imageUrl) throws Exception {
        Image image = new Image();
        image.setImageUrl(imageUrl);

        imageRepository.save(image);

        return image.getId();
    }

    @GetMapping(value = "/{imageId}")
    public String getImageUrl(@PathVariable Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return image.getImageUrl();
    }
}
