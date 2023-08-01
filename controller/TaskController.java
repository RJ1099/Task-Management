package com.codewithrj.taskproject.controller;

import com.codewithrj.taskproject.dto.TaskDto;
import com.codewithrj.taskproject.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping("/{userid}/task")
    public ResponseEntity<TaskDto> saveTask(@PathVariable(name = "userid") Long userid,
                                            @RequestBody TaskDto taskDto){
        TaskDto task = taskService.saveTask(userid, taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name = "userid") Long userid){
        List<TaskDto> tasks = taskService.getAllTasks(userid);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{userid}/task/{taskid}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "userid") Long userid,
                                               @PathVariable(name = "taskid") Long taskid){
        TaskDto task = taskService.getTaskById(userid, taskid);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{userid}/task/{taskid}")
   public ResponseEntity<String> deleteTaskById(@PathVariable(name = "userid") Long userid,
                                                @PathVariable(name = "taskid") Long taskid){
        taskService.deleteTaskById(userid, taskid);
        return ResponseEntity.ok("Task deleted successfully");
   }
}
