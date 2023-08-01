package com.codewithrj.taskproject.service.impl;

import com.codewithrj.taskproject.dto.TaskDto;
import com.codewithrj.taskproject.entity.Task;
import com.codewithrj.taskproject.entity.Users;
import com.codewithrj.taskproject.exceptions.ApiException;
import com.codewithrj.taskproject.exceptions.TaskNotFound;
import com.codewithrj.taskproject.exceptions.UserNotFound;
import com.codewithrj.taskproject.repository.TaskRepository;
import com.codewithrj.taskproject.repository.UsersRepository;
import com.codewithrj.taskproject.service.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Override
    public TaskDto saveTask(Long userid, TaskDto taskDto) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));

        Task task = modelMapper.map(taskDto,Task.class);
        task.setUsers(user);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(Long userid) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));

        List<Task> tasks = taskRepository.findAllByUsersId(userid);
        return tasks.stream().map((task)-> modelMapper.map(task,TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long userid, Long taskid) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));

        Task task = taskRepository.findById(taskid)
                .orElseThrow(()-> new TaskNotFound(String.format("Task with id %d is not found",taskid)));

        if (user.getId() != task.getUsers().getId()){
            throw new ApiException(String.format("Task with id %d is not mapped with the user id %d",taskid,userid));
        }
        return modelMapper.map(task,TaskDto.class);
    }

    @Override
    public void deleteTaskById(Long userid, Long taskid) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));

        Task task = taskRepository.findById(taskid)
                .orElseThrow(()-> new TaskNotFound(String.format("Task with id %d is not found",taskid)));

        if (user.getId() != task.getUsers().getId()){
            throw new ApiException(String.format("Task with id %d is not mapped with the user id %d",taskid,userid));
        }

        taskRepository.deleteById(taskid);
    }
}
