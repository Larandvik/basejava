package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class SqlHelper {

    private SqlHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public static <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection conn = ConnectionManager.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

}
