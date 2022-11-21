package edu.jong.spring.domain.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.jong.spring.common.constants.Packages;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
<<<<<<< HEAD
=======
@EntityScan(basePackages = Packages.ROOT)
@EnableJpaRepositories(basePackages = Packages.ROOT)
>>>>>>> branch 'master' of https://github.com/KIM-JONG-YOUNG/spring-cloud.git
public class DatabaseConfig {

	@Bean  
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    HikariConfig hikariConfig() {
        return new HikariConfig();
    } 
 
    @Bean
    DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }
    
    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
