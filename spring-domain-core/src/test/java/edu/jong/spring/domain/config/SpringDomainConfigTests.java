package edu.jong.spring.domain.config;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.zaxxer.hikari.HikariConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class SpringDomainConfigTests {

	@Autowired
	HikariConfig hikariConfig;

	@Autowired
	DataSource dataSource;
	
	@Test
	void contextLoad() { 
		log.info("JDBC URL         : {}", hikariConfig.getJdbcUrl());
		log.info("DB Username      : {}", hikariConfig.getUsername());
		log.info("DB Password      : {}", hikariConfig.getPassword());
		log.info("DB Max Pool Size : {}", hikariConfig.getMaximumPoolSize());
	}

}
  