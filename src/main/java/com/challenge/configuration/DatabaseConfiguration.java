package com.challenge.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Executes after schema.sql and data.sql
     * @throws IOException
     */
    @PostConstruct
    @Transactional
    public void createTriggers() throws IOException {
        Resource triggerResource = new ClassPathResource("triggers.sql");
        String triggerScript = StreamUtils.copyToString(triggerResource.getInputStream(), StandardCharsets.UTF_8);
        jdbcTemplate.execute(triggerScript);
    }
}
