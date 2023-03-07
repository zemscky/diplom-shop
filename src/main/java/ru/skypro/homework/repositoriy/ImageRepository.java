package ru.skypro.homework.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
