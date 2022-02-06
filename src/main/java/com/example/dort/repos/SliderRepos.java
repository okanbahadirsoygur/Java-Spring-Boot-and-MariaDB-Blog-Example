package com.example.dort.repos;

import com.example.dort.Entities.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface SliderRepos extends JpaRepository<Slider, Long> {
}
