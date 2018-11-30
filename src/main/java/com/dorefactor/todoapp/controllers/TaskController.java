package com.dorefactor.todoapp.controllers;

import com.dorefactor.todoapp.domain.Task;
import com.dorefactor.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/add")
    public String addTask(final Model model) {
        model.addAttribute("task", new Task());
        return "task/add";
    }

    @PostMapping("/add")
    public String submitTask(@ModelAttribute final Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

}
