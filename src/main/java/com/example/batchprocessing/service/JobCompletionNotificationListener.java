package com.example.batchprocessing.service;

import com.example.batchprocessing.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! Job Finished! check results:");
            jdbcTemplate.query("SELECT first_name, last_name FROM people",
                    (resultSet, i) ->
                            new Person(
                                    resultSet.getString(1),
                                    resultSet.getString(2)
                            )
            ).forEach(person -> log.info("Found <" + person + "> in the database."));
        }
    }
}
