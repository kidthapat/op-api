package com.op.merchant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantRepository merchantRepository;

    @GetMapping
    public Merchant create(@RequestBody Merchant merchant){

        return merchantRepository.save(merchant);
    }

    @PostMapping
    public List<Merchant> findall(){

        return merchantRepository.findAll();
    }

    @PutMapping("/{id}")
    public Optional<Merchant> update(@PathVariable ObjectId id, @RequestBody Merchant merchant){
        Optional<Merchant> optional = merchantRepository.findBy_id(id);
        if (optional.isPresent()){
            Merchant existedMerchant = optional.get();
            existedMerchant.setEmail(merchant.getEmail());
            merchantRepository.save(existedMerchant);
        }
        return optional;
    }

    @DeleteMapping("/{id}")
    public List<Merchant> delete(@PathVariable ObjectId id){
        Optional<Merchant> optional = merchantRepository.findBy_id(id);
        if (optional.isPresent()){
            merchantRepository.delete(optional.get());
        }
        return findall();
    }

}
