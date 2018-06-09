package com.imint.example.controllers;

import com.imint.example.repository.TaskGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class TaskGroupController {

    private static Logger logger = LoggerFactory.getLogger(TaskGroupController.class);

    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupController(final TaskGroupRepository taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    @GetMapping("/")
    public ResponseEntity index() {
        logger.info("getOne: {}", taskGroupRepository.findAll());
        return new ResponseEntity("hello world", OK);
    }

}
