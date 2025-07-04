package sk.kasv.balucha.hibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "sk.kasv.balucha.hibernate.dao")
@EnableTransactionManagement
public class DatabaseConfig {
} 