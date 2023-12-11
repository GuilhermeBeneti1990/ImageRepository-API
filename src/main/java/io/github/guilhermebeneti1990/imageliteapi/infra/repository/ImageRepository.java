package io.github.guilhermebeneti1990.imageliteapi.infra.repository;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String > {
}
