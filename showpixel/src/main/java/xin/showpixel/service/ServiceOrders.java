package xin.showpixel.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xin.showpixel.model.Order;
import xin.showpixel.model.OrderItem;
import xin.showpixel.model.Product;
import xin.showpixel.repositories.RepositoryOrder;
import xin.showpixel.repositories.RepositoryOrderItem;
import xin.showpixel.repositories.RepositoryProduct;
import xin.showpixel.request.RequestAddToOrder;
import xin.showpixel.request.RequestSubmitOrder;
import xin.showpixel.response.ResponseApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrders {

    private final RepositoryProduct productRepo;

    private final RepositoryOrderItem orderItemRepo;
    private final RepositoryOrder orderRepo;

    public ServiceOrders(RepositoryProduct productRepo, RepositoryOrderItem orderItemRepo, RepositoryOrder orderRepo) {
        this.productRepo = productRepo;
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
    }

    public ResponseApi<Order> submitOrder(RequestSubmitOrder request){
        Order order = orderRepo.findById(request.getOrderId()).orElseThrow();

        if(!order.getUser().getId().equals(request.getRequestingUserId())) {
            return new ResponseApi<Order>(HttpStatus.UNAUTHORIZED, "This is not your order!", false, null);
        }

        if(!order.isSubmitted()){
            order.setSubmitted(true);
            orderRepo.save(order);
            return new ResponseApi<Order>(HttpStatus.OK, "Submitted", true, order);
        } else {
            return new ResponseApi<>(HttpStatus.CONFLICT, "Order already submitted", false, order);
        }
    }

    public ResponseApi<Order> addProductToOrder(RequestAddToOrder request, String userId){
        System.out.println("ServiceOrders addProductToOrder() " + request);

        Optional<Product> productOpt = productRepo.findById(request.getProductID());
        if(productOpt.isEmpty()){
            return new ResponseApi<>(HttpStatus.NOT_FOUND, "Product not found", false, null);
        }

        Optional<Order> orderOpt = orderRepo.findFirstByUser_IdAndSubmittedFalse(userId);
        boolean saveNew = orderOpt.isEmpty();
        Order order = orderOpt.orElseGet(() ->
        {
           Order o = new Order();
           o.setUserId(userId);
           return o;
        });
        if (saveNew) {
            order = orderRepo.save(order);
        }


        OrderItem oi = new OrderItem();
        oi.setOrder(order);
        oi.setQuantity(request.getQuantity());
        Product p = productOpt.get();
        oi.setProduct(p);
        oi.setPrice(p.getPrice());
        oi = orderItemRepo.save(oi);
        List<OrderItem> allItems = new ArrayList<>();
        if (order.getItems() == null || order.getItems().isEmpty()) {
            allItems.add(oi);
        } else {
            allItems.addAll(order.getItems());
        }

        order.setItems(allItems.stream().toList());

        return new ResponseApi<>(HttpStatus.CREATED, "Add success", true, order);
    }
}
