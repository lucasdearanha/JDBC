package repositories;

import models.User;

import java.util.List;

public interface UserRepository {
    User insert(User user);
    User findById(Long id);
    User update(User user);
    List<User> findAll();
}
