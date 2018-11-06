package com.dorefactor.todoapp.services;

import com.dorefactor.todoapp.domain.Task;
import com.dorefactor.todoapp.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(final long id) {
        return taskRepository.findById(id);
    }

}
