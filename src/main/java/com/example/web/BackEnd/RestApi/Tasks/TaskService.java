package com.example.web.BackEnd.RestApi.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public TaskModel addNewTask(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public TaskModel updateTask(int id, TaskModel taskModel) {
        TaskModel existingTask = taskRepository.findById(id).orElse(null);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(taskModel.getTitle());
        existingTask.setDescription(taskModel.getDescription());
        existingTask.setComplete(taskModel.isComplete());

        return taskRepository.save(existingTask);
    }

    public void removeTask(boolean complete) {
        List<TaskModel> completedTasks = taskRepository.findByComplete(complete);
        taskRepository.deleteAll(completedTasks);
    }
}
