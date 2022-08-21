package repositories.impl;

import db.DB;
import db.exceptions.DatabaseException;
import models.User;
import repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
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

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;

        try {
            st = connection.prepareStatement("""
                    SELECT * FROM tb_user WHERE Id = ?
                    """);

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                user = instantiateUser(rs);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public User update(User user) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement("""
                    UPDATE tb_user SET Name = ?, Phone = ?, Email = ? WHERE tb_user.Id = ?
                    """);

            st.setString(1, user.getName());
            st.setString(2, user.getPhone());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getId());

            rs = st.executeQuery();

            if (rs.next()) {
                user = instantiateUser(rs);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        var users = new ArrayList<User>();

        try {
            st = connection.prepareStatement("""
                    SELECT * FROM tb_user
                    """);

            rs = st.executeQuery();

            while (rs.next()) {
                users.add(instantiateUser(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return users;
    }

    private User instantiateUser(ResultSet rs) throws SQLException {
        var user = new User();
        user.setId(rs.getLong("Id"));
        user.setName(rs.getString("Name"));
        user.setPhone(rs.getString("Phone"));
        user.setEmail(rs.getString("Email"));
        return user;
    }
}
