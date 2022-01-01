package com.example.dort.repos;

import com.example.dort.Entities.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Veritabanı işlemleri repository'ler tarafından yapılır. Veritabanına bu nesne sayesinde erişir ve işlemlerimizi yaparız.
    Her tablonun bir adet repository'si olmak zorundadır.
 */

@Repository
//hangi Entities'i kullanacağımız(Home) ve ilgili Entities'deki primary key'in türünü belirtiyoruz.
public interface HomeRepos extends CrudRepository<Home, Long> {}

