package com.op.merchant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantRepository merchantRepository;

    public Merchant create(@RequestBody Merchant merchant){
        return merchantRepository.save(merchant);
    }

    public List<Merchant> findall(){
        return merchantRepository.findAll();
    }

//    public Optional<Merchant> update(){
//
//    }

    public List<Merchant> delete(@PathVariable ObjectId id){
        Optional<Merchant> optional = merchantRepository.findBy_id(id);
        if (optional.isPresent()){
            merchantRepository.delete(optional.get());
        }
        return findall();
    }

}
