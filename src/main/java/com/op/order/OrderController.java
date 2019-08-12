package com.op.order;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    public Order create(@RequestBody Order order){
        return orderRepository.save(order);
    }

    public List<Order> findall(){
        return orderRepository.findAll();
    }

    public List<Order> delte(@PathVariable ObjectId id){
        Optional<Order> optional = orderRepository.findBy_id(id);
        if(optional.isPresent()){
            orderRepository.delete(optional.get());
        }
        return findall();
    }
}
