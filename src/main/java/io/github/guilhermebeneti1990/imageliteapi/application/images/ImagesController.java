package io.github.guilhermebeneti1990.imageliteapi.application.images;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.Image;
import io.github.guilhermebeneti1990.imageliteapi.domain.enums.ImageExtension;
import io.github.guilhermebeneti1990.imageliteapi.domain.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;

    private static Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {
        logger.info("Image Received -  name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        Image image = Image
                .builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();

        service.save(image);

        return ResponseEntity.ok().build();
    }
}
