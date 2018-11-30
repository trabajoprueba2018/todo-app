package com.dorefactor.todoapp.controllers;

import com.dorefactor.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tasks")
    public String getTasks(final Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "task/index";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(final Model model, @PathVariable("id") final long id) {
        model.addAttribute("task", taskService.getById(id).get());
        return "task/task";
    }

}
