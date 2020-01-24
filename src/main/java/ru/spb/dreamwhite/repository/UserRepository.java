package ru.spb.dreamwhite.repository;

import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.util.SQLHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    SQLHelper sqlHelper;

    public UserRepository(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    public User get(int id) {
        return sqlHelper.transactionalExecute(conn -> {
            User user;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                user = new User(id, rs.getString("email"));
            }
            return user;
        });
    }

    public void save(User user) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO users (id, email) VALUES (?,?)")) {
                        ps.setInt(1, 1);
                        ps.setString(2, user.getEmail());
                        ps.execute();
                    }
                    return null;
                }
        );
    }

}
