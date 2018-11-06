package com.dorefactor.todoapp.repositories;

import com.dorefactor.todoapp.configuration.MySqlDataSourceConfiguration;
import com.dorefactor.todoapp.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ContextConfiguration
@Import({MySqlDataSourceConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskRepositoryIT {

    private static final String INSERT_TASK = "INSERT INTO task (NAME, DESCRIPTION) VALUES (?, ?)";
    private static final String FIND_LAST_ID = "SELECT LAST_INSERT_ID()";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFindAll() {
        final List<Task> tasks = newArrayList();
        tasks.add(createAndSaveTask("list 1", "description 1"));
        tasks.add(createAndSaveTask("list 2", "description 2"));

        final List<Task> actualTasks = taskRepository.findAll();

        assertFalse(actualTasks.isEmpty());
        assertThat(actualTasks.size(), is(2));
    }

    @Test
    public void testFindById() {
        final Task expectedTask = createAndSaveTask("test", "description");

        final Optional<Task> taskHolder = taskRepository.findById(expectedTask.getId());

        assertTrue(taskHolder.isPresent());

        final Task actualTask = taskHolder.get();

        assertThat(actualTask.getId(), is(expectedTask.getId()));
        assertThat(actualTask.getName(), is(expectedTask.getName()));
        assertThat(actualTask.getDescription(), is(expectedTask.getDescription()));
    }

    private Task createAndSaveTask(final String name, final String description) {
        final long id = insertTask(name, description);
        return buildTask(id, name, description);
    }

    private long insertTask(final String name, final String description) {
        jdbcTemplate.update(INSERT_TASK, name, description);
        return jdbcTemplate.queryForObject(FIND_LAST_ID, Long.class);
    }

    private Task buildTask(final long id,
                           final String name,
                           final String description) {
        return Task
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

}
