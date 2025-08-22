package com.productividad.calculo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AlephooDataSourceConfig {
  @Bean(name = "alephooDataSource")
  @org.springframework.boot.context.properties.ConfigurationProperties(prefix = "alephoo.datasource")
  public javax.sql.DataSource alephooDataSource() {
    return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
  }

  @Bean(name = "alephooJdbc")
  public org.springframework.jdbc.core.JdbcTemplate alephooJdbc(
      @org.springframework.beans.factory.annotation.Qualifier("alephooDataSource") javax.sql.DataSource ds) {
    return new org.springframework.jdbc.core.JdbcTemplate(ds);
  }
}
