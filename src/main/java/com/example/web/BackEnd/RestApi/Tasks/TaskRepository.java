package com.example.web.BackEnd.RestApi.Tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

    List<TaskModel> findByComplete(boolean completed);
}
