package xin.showpixel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xin.showpixel.model.User;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


}
