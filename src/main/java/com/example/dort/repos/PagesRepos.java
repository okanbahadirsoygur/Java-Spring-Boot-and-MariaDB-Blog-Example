package com.example.dort.repos;


/*
    Veritabanı işlemleri repository'ler tarafından yapılır. Veritabanına bu nesne sayesinde erişir ve işlemlerimizi yaparız.
    Her tablonun bir adet repository'si olmak zorundadır.
 */

import com.example.dort.Entities.Pages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagesRepos extends CrudRepository<Pages, Long> { }
