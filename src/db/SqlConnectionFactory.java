package db;

import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;

public class SqlConnectionFactory {
    public static UserRepository createUserRepository() {
        return new UserRepositoryImpl(DB.getConnection());
    }
}
