package com.imint.example.repository;

import com.imint.example.domain.TaskGroup;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskGroupRepositoryIT extends AbstractRepositoryIT {

    private static final String INSERT_TASK_GROUP = "INSERT INTO list (NAME, DESCRIPTION) VALUES (?, ?)";
    private static final String FIND_LAST_ID = "SELECT LAST_INSERT_ID()";

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Test
    public void testFindAll() {
        final List<TaskGroup> taskGroups = newArrayList();
        taskGroups.add(createAndSaveTaskGroup("list 1", "description 1"));
        taskGroups.add(createAndSaveTaskGroup("list 2", "description 2"));

        final List<TaskGroup> actualTaskGroups = taskGroupRepository.findAll();

        assertFalse(actualTaskGroups.isEmpty());
        assertThat(actualTaskGroups.size(), is(2));
    }

    @Test
    public void testFingById() {
        final TaskGroup expectedTaskGroup = createAndSaveTaskGroup("test", "description");

        final Optional<TaskGroup> taskGroupHolder = taskGroupRepository.findById(expectedTaskGroup.getId());

        assertTrue(taskGroupHolder.isPresent());

        final TaskGroup actualTaskGroup = taskGroupHolder.get();

        assertThat(actualTaskGroup.getId(), is(expectedTaskGroup.getId()));
        assertThat(actualTaskGroup.getName(), is(expectedTaskGroup.getName()));
        assertThat(actualTaskGroup.getDescription(), is(expectedTaskGroup.getDescription()));
    }

    private TaskGroup createAndSaveTaskGroup(final String name, final String description) {
        final long id = insertTaskGroup(name, description);
        return buildTaskGroup(id, name, description);
    }

    private long insertTaskGroup(final String name, final String description) {
        jdbcTemplate.update(INSERT_TASK_GROUP, name, description);
        return jdbcTemplate.queryForObject(FIND_LAST_ID, Long.class);
    }

    private TaskGroup buildTaskGroup(final long id,
                                     final String name,
                                     final String description) {
        return TaskGroup
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

}
