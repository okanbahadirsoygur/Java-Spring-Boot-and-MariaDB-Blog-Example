package com.example.dort.repos;

import com.example.dort.Entities.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepos extends CrudRepository<Categories, Long> {
}
