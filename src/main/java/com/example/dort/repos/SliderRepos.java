package com.example.dort.repos;

import com.example.dort.Entities.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Transactional
@Repository
public interface SliderRepos extends CrudRepository<Slider, Long> {

    //pure sql(nativeQuery) olarak tabloya erişiyoruz.
    //Obje üzerinden update işleminin farklı bir yolu var(Controller üzerinden yapılıyor) ama ben geleneksel yola göre gitmek istedim.
    @Modifying
    @Query(value = "update slider set title = ?2, img_url = ?3, rank = ?4, url = ?5 where id = ?1",nativeQuery = true)
    public void updateSliderById(Long id, String title, String img_url, int rank, String url);


    //obje üzerinden tabloya erişelim
    @Query("select s from Slider s order by s.rank")
    public List<Slider> getSlidersOrderByRank();

}
