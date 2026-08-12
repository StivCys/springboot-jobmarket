package com.jobmarket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Teste de integração que valida a infraestrutura:
 * - Testcontainers sobe um Postgres isolado
 * - Flyway executa as migrations
 * - O contexto Spring carrega corretamente
 */
@SpringBootTest
@ActiveProfiles("test")
class IntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoadsWithTestcontainersAndFlyway() throws Exception {
        try (Connection conn = dataSource.getConnection();
             ResultSet rs = conn.createStatement()
                     .executeQuery("SELECT count(*) FROM flyway_schema_history")) {
            assertTrue(rs.next(), "Flyway schema_history deve existir");
            assertTrue(rs.getInt(1) >= 1, "Pelo menos uma migration deve ter sido executada");
        }
    }
}
