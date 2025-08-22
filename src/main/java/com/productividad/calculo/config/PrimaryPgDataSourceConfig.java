package com.productividad.calculo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class PrimaryPgDataSourceConfig {

  @Bean(name = "dataSource")
  @Primary
  public DataSource pgDataSource(
      @Value("${spring.datasource.url}") String url,
      @Value("${spring.datasource.username}") String user,
      @Value("${spring.datasource.password}") String pass,
      @Value("${spring.datasource.driver-class-name:}") String driver // opcional
  ) {
    HikariConfig cfg = new HikariConfig();
    cfg.setJdbcUrl(url);          // <- CLAVE: usar jdbcUrl
    cfg.setUsername(user);
    cfg.setPassword(pass);
    if (!driver.isBlank()) cfg.setDriverClassName(driver);
    // (opcional) cfg.setMaximumPoolSize(10); cfg.setMinimumIdle(1);
    return new HikariDataSource(cfg);
  }
}
