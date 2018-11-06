package com.dorefactor.todoapp.controllers;

import com.dorefactor.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
