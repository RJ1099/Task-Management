package com.codewithrj.taskproject.service;

import com.codewithrj.taskproject.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto saveTask(Long userid, TaskDto taskDto);
    List<TaskDto> getAllTasks(Long userid);
    TaskDto getTaskById(Long userid, Long taskid);
    void deleteTaskById(Long userid, Long taskid);
}
