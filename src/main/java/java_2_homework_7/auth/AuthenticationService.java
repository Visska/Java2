package java_2_homework_7.auth;


import java_2_homework_7.entity.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> doAuth(String login, String password);
}
