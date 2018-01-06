package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.core.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.SqlGeneratorSource;
import org.springframework.data.jdbc.mapping.model.JdbcMappingContext;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@SpringBootApplication
@EnableJdbcRepositories
public class DataJdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJdbcDemoApplication.class, args);
	}

	@Autowired
	JdbcMappingContext context;
	@Autowired
	DataSource datasource;

	@Bean
	DataAccessStrategy dataAccessStrategy() {
	    return new DefaultDataAccessStrategy(
	            new SqlGeneratorSource(context),
	            new NamedParameterJdbcTemplate(datasource),
	            context);
	}

	@Bean
	NamingStrategy namingStrategy() {
	    return new CustomNamingStrategy();
	}
}
