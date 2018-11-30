package com.dorefactor.todoapp.controllers;

import com.dorefactor.todoapp.domain.Task;
import com.dorefactor.todoapp.services.TaskService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TaskControllerIT {

    @MockBean
    private TaskService taskService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void testGetHome() throws Exception {
        mockMvc()
                .perform(
                        get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
        ;
    }

    @Test
    public void testGetTasks() throws Exception {
        final Task task = Task
                .builder()
                .id(1L)
                .name("Task A")
                .build();


        when(taskService.getAll()).thenReturn(singletonList(task));

        mockMvc()
                .perform(
                        get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/index"))
                .andExpect(model().attribute("tasks", hasSize(1)))

        ;

        verify(taskService).getAll();
    }

    @Test
    public void testGetAddTask() throws Exception {
        mockMvc()
                .perform(
                        get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/add"))
                .andExpect(model().attribute("task", is(not(nullValue()))))

        ;
    }

    @Test
    public void testPostAddTask() throws Exception {
        final Task task = Task
                .builder()
                .id(1L)
                .name("Task A")
                .build();

        mockMvc()
                .perform(
                        post("/add")
                                .contentType(APPLICATION_FORM_URLENCODED)
                                .sessionAttr("task", any(Task.class))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tasks"))
        ;

        verify(taskService).addTask(Mockito.any(Task.class));
    }

    private MockMvc mockMvc() {
        return webAppContextSetup(webApplicationContext)
                .build();
    }

}
