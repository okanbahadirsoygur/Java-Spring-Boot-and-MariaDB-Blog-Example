package com.okanbahadirsoygur.spring_boot_blog.repos;

import com.okanbahadirsoygur.spring_boot_blog.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface AdminRepos extends JpaRepository<Admin, Long> {

    @Query("select a from admin a where a.email = ?1  and a.pass = ?2")
    Admin findByEmailandPass(String email, String pass);
}
