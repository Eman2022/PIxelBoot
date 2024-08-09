package xin.showpixel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xin.showpixel.model.Product;
import xin.showpixel.repositories.RepositoryProduct;
import xin.showpixel.request.RequestProducts;
import xin.showpixel.response.ResponseApi;

import java.util.List;


@Service
public class ServiceProduct {

    private final RepositoryProduct repository;

    public ServiceProduct(RepositoryProduct repository) {
        this.repository = repository;
    }

    public ResponseApi<Product> addNewProduct(Product product) {
        System.out.println("ProductService addNewProduct " + product);

        if(!isValidProduct(product)) {
            return new ResponseApi<>(HttpStatus.BAD_REQUEST, "Invalid Product Data", false, null);
        }
        repository.save(product);
        return new ResponseApi<>(HttpStatus.CREATED, "Product registered", true, product);
    }


    public ResponseApi<List<Product>> getProducts(RequestProducts request) {
        PageRequest pr = PageRequest.of(request.getPage(), request.getNumberPerPage());
        Page<Product> r = repository.findAll(pr);
        return new ResponseApi<>(HttpStatus.OK,"success", true, r.stream().toList());
    }


    private boolean isValidProduct(Product product){
        //TODO: check for valid products
        return true;
    }
}
