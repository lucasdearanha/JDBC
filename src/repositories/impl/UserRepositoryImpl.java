package repositories.impl;

import db.exceptions.DatabaseException;
import models.User;
import repositories.UserRepository;

import java.sql.*;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User insert(User user) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement("""
                    INSERT INTO tb_user (
                        Name,
                        Phone,
                        Email
                    )
                    VALUES(?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, user.getName());
            st.setString(2, user.getPhone());
            st.setString(3, user.getEmail());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    user.setId(id);
                }
            } else {
                throw new DatabaseException("User not inserted");
            }

            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
