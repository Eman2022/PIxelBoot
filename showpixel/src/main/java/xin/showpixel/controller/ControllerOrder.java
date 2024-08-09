package xin.showpixel.controller;

import org.springframework.http.HttpStatus;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.showpixel.model.Order;
import xin.showpixel.model.User;
import xin.showpixel.request.RequestAddToOrder;
import xin.showpixel.request.RequestSubmitOrder;
import xin.showpixel.response.ResponseApi;
import xin.showpixel.service.ServiceOrders;

@RestController
@RequestMapping("/order")
public class ControllerOrder {

    private final ServiceOrders orderService;

    public ControllerOrder(ServiceOrders orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/submit")
    public ResponseApi<Order> submitOrder(@RequestBody RequestSubmitOrder request){
        if(request.getOrderId().isEmpty() || request.getOrderId() == null){
            return new ResponseApi<>(HttpStatus.NO_CONTENT, "Need order id!", false, null);
        }
        System.out.println("ControllerOrder submitOrder() " + request);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String userId = ((User)principal).getId();
            request.setRequestingUserId(userId);
            return orderService.submitOrder(request);
        } else {
            return new ResponseApi<>(HttpStatus.FORBIDDEN, "Login to submit", false, null);
        }
    }

    @PutMapping("/add")
    public ResponseApi<Order> addProductToOrder(@RequestBody RequestAddToOrder request){
        System.out.println("ProductController addProductToOrder() " + request);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String userId = ((User)principal).getId();
            return orderService.addProductToOrder(request, userId);
        } else {
            return new ResponseApi<>(HttpStatus.FORBIDDEN, "Login to add to cart", false, null);
        }
    }
}
