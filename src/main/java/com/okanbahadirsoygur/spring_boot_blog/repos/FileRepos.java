package com.okanbahadirsoygur.spring_boot_blog.repos;

import com.okanbahadirsoygur.spring_boot_blog.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepos extends JpaRepository<File, Long> {


}
