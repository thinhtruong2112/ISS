package com.project.assignment.config;

import com.project.assignment.model.*;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.project.assignment.repository",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Author1Details author1Details;
    @Autowired
    private Author2Details author2Details;
    @Autowired
    private Editor1Details editor1Details;
    @Autowired
    private Editor2Details editor2Details;
    @Autowired
    private ProgramChair1Details programChair1Details;
    @Autowired
    private ProgramChair2Details programChair2Details;
    @Autowired
    private Reviewer1Details reviewer1Details;
    @Autowired
    private Reviewer2Details reviewer2Details;
    @Autowired
    private TrackChair1Details trackChair1Details;
    @Autowired
    private TrackChair2Details trackChair2Details;

    @Bean
    @Primary
    @Autowired
    public DataSource dataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();
        routingDataSource.initDatasource(
            author1DataSource(), author2DataSource(),
            editor1DataSource(), editor2DataSource(),
            reviewer1DataSource(), reviewer2DataSource(),
            trackChair1DataSource(), trackChair2DataSource(),
            programChair1DataSource(), programChair2DataSource()
        );
        return routingDataSource;
    }

    private DataSource programChair1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(programChair1Details.getUrl());
        dataSource.setUsername(programChair1Details.getUsername());
        dataSource.setPassword(programChair1Details.getPassword());
        return dataSource;
    }

    private DataSource programChair2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(programChair2Details.getUrl());
        dataSource.setUsername(programChair2Details.getUsername());
        dataSource.setPassword(programChair2Details.getPassword());
        return dataSource;
    }

    private DataSource trackChair2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(trackChair2Details.getUrl());
        dataSource.setUsername(trackChair2Details.getUsername());
        dataSource.setPassword(trackChair2Details.getPassword());
        return dataSource;
    }

    private DataSource trackChair1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(trackChair1Details.getUrl());
        dataSource.setUsername(trackChair1Details.getUsername());
        dataSource.setPassword(trackChair1Details.getPassword());
        return dataSource;
    }

    private DataSource reviewer2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(reviewer2Details.getUrl());
        dataSource.setUsername(reviewer2Details.getUsername());
        dataSource.setPassword(reviewer2Details.getPassword());
        return dataSource;
    }

    private DataSource reviewer1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(reviewer1Details.getUrl());
        dataSource.setUsername(reviewer1Details.getUsername());
        dataSource.setPassword(reviewer1Details.getPassword());
        return dataSource;
    }

    private DataSource editor1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(editor1Details.getUrl());
        dataSource.setUsername(editor1Details.getUsername());
        dataSource.setPassword(editor1Details.getPassword());
        return dataSource;
    }

    private DataSource editor2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(editor2Details.getUrl());
        dataSource.setUsername(editor2Details.getUsername());
        dataSource.setPassword(editor2Details.getPassword());
        return dataSource;
    }

    private DataSource author1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(author1Details.getUrl());
        dataSource.setUsername(author1Details.getUsername());
        dataSource.setPassword(author1Details.getPassword());
        return dataSource;
    }

    private DataSource author2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(author2Details.getUrl());
        dataSource.setUsername(author2Details.getUsername());
        dataSource.setPassword(author2Details.getPassword());
        return dataSource;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource()).packages("com.project.assignment.entity").build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
        @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
