package com.dorefactor.todoapp.services;

import com.dorefactor.todoapp.domain.Task;
import com.dorefactor.todoapp.repositories.TaskRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @Before
    public void setUp() {
        initMocks(this);
        taskService = new TaskService(taskRepository);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testGetAll() {
        final Task task = buildTask(1L, "Task A");

        when(taskRepository.findAll()).thenReturn(singletonList(task));

        final List<Task> tasks = taskService.getAll();

        assertThat(tasks.size(), is(1));
        verify(taskRepository).findAll();
    }

    @Test
    public void testGetById() {
        final Task task = buildTask(1L, "Task A");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        final Optional<Task> actualTask = taskService.getById(1L);

        assertTrue(actualTask.isPresent());
        assertThat(actualTask.get(), is(task));
        verify(taskRepository).findById(1L);
    }

    @Test
    public void testAddTask() {
        final Task task = buildTask(1L, "Task A");

        when(taskRepository.insert(task)).thenReturn(task);

        final Optional<Task> actualTask = taskService.addTask(task);

        assertTrue(actualTask.isPresent());
        assertThat(actualTask.get(), is(task));
        verify(taskRepository).insert(task);
    }

    private Task buildTask(final long id,
                           final String name) {
        return Task
                .builder()
                .id(id)
                .name(name)
                .build();
    }

}
