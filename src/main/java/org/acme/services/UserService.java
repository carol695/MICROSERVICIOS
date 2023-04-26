package org.acme.services;

import org.acme.model.Stream;
import org.acme.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(ObjectId id);

    Stream updateUser(String id, User user);

    void deleteUser(String id);

    User getUserByEmail(String email);
}
