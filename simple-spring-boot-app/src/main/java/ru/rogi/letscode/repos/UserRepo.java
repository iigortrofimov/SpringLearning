package ru.rogi.letscode.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rogi.letscode.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
