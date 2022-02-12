package com.example.dort.repos;

import com.example.dort.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

@Repository
public interface CategoriesRepos extends JpaRepository<Categories, Long> {

    //burda yazılan query veritabanındaki tablolar ile yazılmıyor. Objeler üzerinden ve objelerin içerisindeki değişkenler üzerinden sql yazılıyor.
    //Veritabanında tablomun adı categories iken ben burada Categories şeklinde yazdım. Çünkü objemin ismi bu şekilde.
    @Query("select c from Categories c where c.slug = ?1")
    Categories findBySlug(String slug);

    @Modifying
    @Query(value = "update categories set title = ?2, slug = ?3, description = ?4, rank = ?5 where id = ?1",nativeQuery = true)
    void updateCategorieById(Long id, String title, String slug, String description, Long rank);
}
