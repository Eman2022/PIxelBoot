package xin.showpixel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xin.showpixel.model.Product;


import java.util.Optional;

@Repository
public interface RepositoryProduct extends CrudRepository<Product, String> {

    Optional<Product> findByTitle(String title);

    Page<Product> findAll(Pageable pageable);
}
