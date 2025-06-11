package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

@Log4j2
public class JDBCTest {
    @BeforeAll
    public static void setUp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("JDBC Driver Connect OK")
    public void testConnect() {
        String url = "jdbc:mysql://localhost:3306/scoula_db";
        try (Connection conn = DriverManager.getConnection(url, "scoula", "1234")) {
            log.info(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
