package io.github.guilhermebeneti1990.imageliteapi.application.images;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.Image;
import io.github.guilhermebeneti1990.imageliteapi.domain.services.ImageService;
import io.github.guilhermebeneti1990.imageliteapi.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }

}
