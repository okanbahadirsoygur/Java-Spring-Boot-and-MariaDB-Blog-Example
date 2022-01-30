package com.example.dort.repos;


/*
    Veritabanı işlemleri repository'ler tarafından yapılır. Veritabanına bu nesne sayesinde erişir ve işlemlerimizi yaparız.
    Her tablonun bir adet repository'si olmak zorundadır.
 */

import com.example.dort.Entities.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagesRepos extends JpaRepository<Pages, Long> {


    @Query(value = "select * from Pages p order by p.created_time desc LIMIT 10",nativeQuery = true)
    public List<Pages> findAllLimit10();


    @Query(value = "select p from Pages p where p.slug = ?1")
    public Pages findBySlug(String slug);


    @Query(value = "select p from Pages p where p.categorie_id = ?1")
    public List<Pages> findByCategorie_id(Long categorie_id);


    @Query(value = "select p from Pages p where p.title LIKE %?1% or p.sub_title LIKE %?1% or p.data LIKE %?1% or p.short_data LIKE %?1% or p.sub_title LIKE %?1%")
    public List<Pages> searchByLike(String query);

}
