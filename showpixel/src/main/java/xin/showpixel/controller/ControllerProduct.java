package xin.showpixel.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.showpixel.model.Product;
import xin.showpixel.response.ResponseApi;
import xin.showpixel.service.ServiceProduct;

@RestController
@RequestMapping("/product")
public class ControllerProduct {

    private final ServiceProduct productService;

    public ControllerProduct(ServiceProduct productService) {
        this.productService = productService;
    }


    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    @PutMapping("/add")
    public ResponseApi<Product> addNewProduct(@RequestBody Product product) {
        System.out.println("ProductController addNewProduct() " + product);
        return productService.addNewProduct(product);
    }


}
