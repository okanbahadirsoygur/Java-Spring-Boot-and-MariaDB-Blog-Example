package com.example.dort.repos;

import com.example.dort.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepos extends JpaRepository<Categories, Long> {

    //burda yazılan query veritabanındaki tablolar ile yazılmıyor. Objeler üzerinden ve objelerin içerisindeki değişkenler üzerinden sql yazılıyor.
    //Veritabanında tablomun adı categories iken ben burada Categories şeklinde yazdım. Çünkü objemin ismi bu şekilde.
    @Query("select c from Categories c where c.slug = ?1")
    Categories findBySlug(String slug);
}
