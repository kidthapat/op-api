package com.op.merchant;

import com.op.constant.Api;
import com.op.user.User;
import com.op.user.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping(Api.v1)
@RestController
public class MerchantController {
    private static Log LOG = LogFactory.getLog(MerchantController.class);

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

    @PutMapping("/merchants/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Merchant merchant) {
        LOG.info("Call Update Merchant: " + id);
        Optional<Merchant> optional = merchantService.updateById(id, merchant);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/merchants/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Optional<Merchant> optional = merchantService.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
