package com.examle.democicd.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.examle.democicd.repository")
@EnableTransactionManagement
@ActiveProfiles("test")
public class H2JpaConfig {
	
	@Autowired
    private Environment env;
     
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"/*env.getProperty("jdbc.url")*/);
        dataSource.setUsername("sa"/*env.getProperty("jdbc.user")*/);
        dataSource.setPassword("sa"/*env.getProperty("jdbc.pass")*/);
 
        return dataSource;
    }
   
}