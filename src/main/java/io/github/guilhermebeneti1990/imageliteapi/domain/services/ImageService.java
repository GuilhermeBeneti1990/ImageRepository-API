package io.github.guilhermebeneti1990.imageliteapi.domain.services;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);
    Optional<Image> findById(String id);
}
