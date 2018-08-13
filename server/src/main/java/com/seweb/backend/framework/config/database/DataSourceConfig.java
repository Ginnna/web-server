package com.seweb.backend.framework.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("config/datasource.properties")
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

}
