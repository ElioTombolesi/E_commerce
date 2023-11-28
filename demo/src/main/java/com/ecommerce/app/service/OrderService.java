package com.ecommerce.app.service;

import com.ecommerce.app.entities.Order;
import com.ecommerce.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order create(Order order) throws Exception {
        orderValidations(order);
        discountStock(order);
        return orderRepository.save(order);
    }

    public Optional<Order> loadOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    private boolean orderValidations(Order order) throws Exception {
        for (var orderItem: order.getOrderItems()) {
            var productId = orderItem.getProduct().getId();
            if (productId != null || productId == 0){
                var product = productService.loadProductById(productId);
                if (product.get().getStock() < orderItem.getQuantity()){
                    throw new Exception("El producto " + product.get().getName() + " no tiene stock suficiente.");
                }
            }else {
                throw new Exception("Ocurrio un error al momento de recuperar los productos");
            }
        }
        return true;
    }
    private void discountStock(Order order) throws Exception {
        for (var orderItem: order.getOrderItems()) {
            var productId = orderItem.getId();
            if (productId != null){
                var product = productService.loadProductById(productId);
                var newStock = product.get().getStock() - orderItem.getQuantity();
                product.get().setStock(newStock);
                productService.update(product.get());
            }
        }
    }
}
