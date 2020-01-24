package ru.spb.dreamwhite.util;

import org.postgresql.util.PSQLException;
import ru.spb.dreamwhite.exception.ExistStorageException;
import ru.spb.dreamwhite.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}