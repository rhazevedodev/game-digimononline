package br.com.digimon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FlywayMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testFlywayMigrations() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM usuario", Integer.class);
        assertThat(count).isNotNull();
    }
}
