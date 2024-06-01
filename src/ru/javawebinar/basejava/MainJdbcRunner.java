package ru.javawebinar.basejava;

import ru.javawebinar.basejava.util.ConnectionManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public class MainJdbcRunner {

    public static void main(String[] args) throws SQLException, RuntimeException {
        try (Connection connection = ConnectionManager.get()) {
            System.out.println(connection.getTransactionIsolation());
            ConnectionManager.closePool();

        } finally {
            ConnectionManager.closePool();
        }

        try {
            Field pool = ConnectionManager.class.getDeclaredField("sourceConnections");
            pool.setAccessible(true);
            System.out.println(pool);
            System.out.println(pool.get(ConnectionManager.class));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
