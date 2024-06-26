package ru.javawebinar.basejava.storage;

public interface SqlStorageTrain {
    String DELETE_ALL = """
            DELETE
            FROM resume
            WHERE TRUE;
            """;

    String UPDATE_RESUME = """
            UPDATE resume
            SET full_name = ?
            WHERE uuid = ?;
            """;

    String DELETE_CONTACT_RESUME = """
            DELETE
            FROM contact
            WHERE resume_uuid = ?
            """;

    String SAVE_RESUME = """
            INSERT INTO resume (uuid, full_name)
            VALUES(?,?);
            """;

    String SAVE_CONTACT_RESUME = """
            INSERT INTO contact(resume_uuid, type, value)
            VALUES (?,?,?);
            """;

    String GET_RESUME = """
            SELECT *
            FROM resume r
                LEFT JOIN contact c
                  ON r.uuid = c.resume_uuid
            WHERE r.uuid = ?;
            """;

    String DELETE_RESUME = """
            DELETE
            FROM resume
            WHERE uuid = ?;
            """;

    String GET_ALL_SORTED = """
            SELECT *
            FROM resume r
            LEFT JOIN contact c ON r.uuid = c.resume_uuid
            ORDER BY full_name, uuid
            """;

    String GET_SIZE = """
            SELECT count(uuid)
            FROM resume
            """;
}
