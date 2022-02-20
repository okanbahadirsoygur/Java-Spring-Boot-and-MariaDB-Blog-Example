package com.okanbahadirsoygur.spring_boot_blog.repos;

import com.okanbahadirsoygur.spring_boot_blog.Entities.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
    Veritabanı işlemleri repository'ler tarafından yapılır. Veritabanına bu nesne sayesinde erişir ve işlemlerimizi yaparız.
    Her tablonun bir adet repository'si olmak zorundadır.
 */

@Repository
//hangi Entities'i kullanacağımız(Home) ve ilgili Entities'deki primary key'in türünü belirtiyoruz.
public interface HomeRepos extends CrudRepository<Home, Long> {}

