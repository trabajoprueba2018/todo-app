package com.dorefactor.todoapp.repositories;

import com.dorefactor.todoapp.domain.Task;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private final SqlSession sqlSession;

    public TaskRepository(final SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Task> findAll() {
        return sqlSession.selectList("com.dorefactor.todoapp.repositories.TaskRepository.findAll");
    }

    public Optional<Task> findById(final long id) {
        return Optional.ofNullable(sqlSession.selectOne("com.dorefactor.todoapp.repositories.TaskRepository.findById", id));
    }

}
