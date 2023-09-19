package com.example.web.BackEnd.RestApi.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskManagement")
@CrossOrigin(origins = "http://localhost:63342")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new-task")
    public ResponseEntity<TaskModel> addTask(@RequestBody TaskModel taskModel) {
        TaskModel newTask = taskService.addNewTask(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable int id, @RequestBody TaskModel taskModel) {

        TaskModel updatedTask = taskService.updateTask(id, taskModel);

        if (updatedTask != null) {
            System.out.println("Found");
            return ResponseEntity.ok(updatedTask);
        } else {
            System.out.println("Not Found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-completed-task/{completed}")
    public void removeCompletedTask(@PathVariable boolean completed) {
        taskService.removeTask(completed);
    }
}
