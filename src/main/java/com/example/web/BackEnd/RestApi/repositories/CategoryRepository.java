package com.example.web.BackEnd.RestApi.repositories;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {


}
