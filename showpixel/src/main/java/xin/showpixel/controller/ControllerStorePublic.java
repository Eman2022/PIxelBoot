package xin.showpixel.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.showpixel.model.Product;
import xin.showpixel.request.RequestProducts;
import xin.showpixel.response.ResponseApi;
import xin.showpixel.service.ServiceProduct;

import java.util.List;

@RestController
@RequestMapping("/store")
public class ControllerStorePublic {

    private final ServiceProduct productService;

    public ControllerStorePublic(ServiceProduct productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public ResponseApi<List<Product>> getProducts(@RequestBody RequestProducts request){
        System.out.println("ControllerStore getProducts() " + request);
        return productService.getProducts(request);
    }

}
