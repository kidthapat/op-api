package com.op.merchant;

import com.op.constant.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(Api.v1)
@RestController
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchants")
    public ResponseEntity create(@RequestBody Merchant merchant) {
        Merchant createdMerchant = merchantService.create(merchant);
        return new ResponseEntity(createdMerchant, HttpStatus.CREATED);
    }

    @GetMapping("/merchants")
    public List<Merchant> findAll() {
        return merchantService.findAll();
    }

//    @PutMapping("/merchants/{id}")
//    public Optional<Merchant> update(@PathVariable String id, @RequestBody Merchant merchant) {
//        Optional<Merchant> optional = merchantService.findAll(id);
//        if (optional.isPresent()) {
//            Merchant existedMerchant = optional.get();
//            existedMerchant.setEmail(merchant.getEmail());
//            merchantRepository.save(existedMerchant);
//        }
//        return optional;
//    }

    @DeleteMapping("/merchants/{id}")
    public List<Merchant> delete(@PathVariable ObjectId id) {
        Optional<Merchant> optional = merchantRepository.findBy_id(id);
        if (optional.isPresent()) {
            merchantRepository.delete(optional.get());
        }
        return findAll();
    }
}
