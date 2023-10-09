package com.example.web.BackEnd.RestApi.repositories;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    CategoryModel findByGenre(String genre);
}
