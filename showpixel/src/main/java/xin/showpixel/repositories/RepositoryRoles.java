package xin.showpixel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xin.showpixel.model.Role;


@Repository
public interface RepositoryRoles extends JpaRepository<Role, Integer> {


}
