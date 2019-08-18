package com.op.order;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping()
    public Order create(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PostMapping("/{id}")
    public List<Order> findall(){
        return orderRepository.findAll();
    }

    @PutMapping("/{id}")
    public Optional<Order> update(@PathVariable ObjectId id, @RequestBody Order order){
        Optional<Order> optional = orderRepository.findBy_id(id);
        if (optional.isPresent()){
            Order existedUser = optional.get();
            existedUser.setCancle(order.getCancle());
            orderRepository.save(existedUser);
        }
        return optional;
    }

    @DeleteMapping("/id")
    public List<Order> delte(@PathVariable ObjectId id){
        Optional<Order> optional = orderRepository.findBy_id(id);
        if(optional.isPresent()){
            orderRepository.delete(optional.get());
        }
        return findall();
    }
}
