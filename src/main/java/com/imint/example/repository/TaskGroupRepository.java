package com.imint.example.repository;

import com.imint.example.domain.TaskGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskGroupRepository extends AbstractRepository {

    public List<TaskGroup> findAll() {
        return sqlSession.selectList("com.imint.example.repository.TaskGroupRepository.findAll");
    }

    public Optional<TaskGroup> findById(final long id) {

        return Optional.ofNullable(sqlSession.selectOne("com.imint.example.repository.TaskGroupRepository.findById", id));
    }

}
