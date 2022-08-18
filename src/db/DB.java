package db;

import db.exceptions.DatabaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    private static Properties loadProperties() {
        try (var fs = new FileInputStream("db.properties")) {
            var properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
        return connection;
    }
}
