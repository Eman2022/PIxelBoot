package xin.showpixel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xin.showpixel.model.OrderItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryOrderItem extends JpaRepository<OrderItem, Long> {

    Optional<List<OrderItem>> findAllByOrder_Id(String orderId);
}
