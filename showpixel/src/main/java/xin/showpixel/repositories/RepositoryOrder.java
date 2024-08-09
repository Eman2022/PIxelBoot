package xin.showpixel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xin.showpixel.model.Order;
import xin.showpixel.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryOrder extends CrudRepository<Order, String> {
    Optional<List<Order>> findAllByUser_Id(User user);

    Optional<Order> findFirstByUser_IdAndSubmittedFalse(String userid);
}
