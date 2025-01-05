package in.kkpit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kkpit.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Correct method name following Spring Data JPA conventions
   boolean existsByEmail(String email);
  // Optional<User>findByEmail(String email);
Optional<User> findByEmail(String username);
}
