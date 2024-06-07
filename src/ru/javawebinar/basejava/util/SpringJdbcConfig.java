package ru.javawebinar.basejava.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public final class SpringJdbcConfig {

    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    private SpringJdbcConfig() {
    }

    private static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USERNAME_KEY),
                PropertiesUtil.get(PASSWORD_KEY)
        );
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }

    public static JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
