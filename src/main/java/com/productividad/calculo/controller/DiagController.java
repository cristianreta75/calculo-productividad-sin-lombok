package com.productividad.calculo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.zaxxer.hikari.HikariDataSource;
import java.util.*;

@RestController
@RequestMapping("/_diag")
class DiagController {
  @Autowired DataSource dataSource; // primario JPA
  @Autowired @Qualifier("alephooDataSource") DataSource alephooDs;

  @GetMapping("/ds")
  public Map<String,String> ds() throws Exception {
    var m = new LinkedHashMap<String,String>();
    try (var c = dataSource.getConnection()) {
      m.put("primary.url", c.getMetaData().getURL());
      m.put("primary.user", c.getMetaData().getUserName());
      m.put("primary.driver", c.getMetaData().getDriverName());
    }
    try (var c = alephooDs.getConnection()) {
      m.put("alephoo.url", c.getMetaData().getURL());
      m.put("alephoo.user", c.getMetaData().getUserName());
      m.put("alephoo.driver", c.getMetaData().getDriverName());
    }
    return m;
  }
}