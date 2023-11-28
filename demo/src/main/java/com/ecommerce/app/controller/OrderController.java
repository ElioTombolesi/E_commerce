package com.ecommerce.app.controller;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.loadOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (orderService.loadOrderById(id) != null) {
            orderService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping()
    public ResponseEntity<Order> update(@RequestBody Order order) {
        ResponseEntity<Order> response = null;

        Optional<Order> userDetails = orderService.loadOrderById(order.getId());
        if (userDetails != null) {
            response = ResponseEntity.ok(orderService.update(order));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Order order) throws Exception {
            var productSaved = orderService.create(order);
            return new ResponseEntity(HttpStatus.OK);
    }
}
