package com.imint.example.repository;

import com.imint.example.configuration.MySqlDataSourceConfiguration;
import com.imint.example.domain.AbstractIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ContextConfiguration
@Import({
                MySqlDataSourceConfiguration.class
        })

public abstract class AbstractRepositoryIT extends AbstractIT {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

}
