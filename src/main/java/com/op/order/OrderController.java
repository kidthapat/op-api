package com.op.order;

import com.op.constant.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(Api.v1)
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

//     public int calprice(int totalpage,int copy,String typeprint,String sizepaper){
//         String blackwhite,color,laser,photo,a1,a2,a3,a4;
//         blackwhite = "bw";
//         color = "color";
//         photo = "photo";
//         laser = "laser";
//         a1 = "a1";
//         a2 = "a2";
//         a3 = "a3";
//         a4 = "a4";
//         int price = 0;
//            if(typeprint.equals("bw")&&sizepaper.equals("a1")){
//                price = totalpage * copy * 1 * 60;
//            } else if(typeprint.equals("bw")&&sizepaper.equals("a2")){
//                price = totalpage * copy * 1 * 25;
//            } else if(typeprint.equals("bw")&&sizepaper.equals("a3")){
//                price = totalpage * copy * 1 * 30;
//            } else if(typeprint.equals("bw")&&sizepaper.equals("a4")){
//                price = totalpage * copy * 1 * 1;
//            } else if(typeprint.equals("color")&&sizepaper.equals("a1")){
//                price = totalpage * copy * 3 * 60;
//            } else if(typeprint.equals("color")&&sizepaper.equals("a2")){
//                price = totalpage * copy * 3 * 25;
//            } else if(typeprint.equals("color")&&sizepaper.equals("a3")){
//                price = totalpage * copy * 3 * 30;
//            } else if(typeprint.equals("color")&&sizepaper.equals("a4")){
//                price = totalpage * copy * 3 * 1;
//            } else if(typeprint.equals("photo")&&sizepaper.equals("a1")){
//                price = totalpage * copy * 10 * 60;
//            } else if(typeprint.equals("photo")&&sizepaper.equals("a2")){
//                price = totalpage * copy * 10 * 25;
//            } else if(typeprint.equals("photo")&&sizepaper.equals("a3")){
//                price = totalpage * copy * 10 * 30;
//            } else if(typeprint.equals("photo")&&sizepaper.equals("a4")){
//                price = totalpage * copy * 10 * 1;
//            } else if(typeprint.equals("laser")&&sizepaper.equals("a1")){
//                price = totalpage * copy * 5 * 60;
//            } else if(typeprint.equals("laser")&&sizepaper.equals("a2")){
//                price = totalpage * copy * 5 * 25;
//            } else if(typeprint.equals("laser")&&sizepaper.equals("a3")){
//                price = totalpage * copy * 5 * 30;
//            } else if(typeprint.equals("laser")&&sizepaper.equals("a4")){
//                price = totalpage * copy * 5 * 1;
//            }
//         return price;
//     }
}
