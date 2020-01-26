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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(Api.v1)
@RestController
public class MerchantController {
    private static Log LOG = LogFactory.getLog(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @PreAuthorize("hasAuthority('CREATE_MERCHANT')")
    @PostMapping("/merchants")
    public ResponseEntity create(@RequestBody Merchant merchant) {
        LOG.info("Call Create Merchant");
        Merchant createdMerchant = merchantService.create(merchant);
        return new ResponseEntity(createdMerchant, HttpStatus.CREATED);
    }

    @GetMapping("/merchants")
    public ResponseEntity findAll() {
        LOG.info("Call Get All Merchants");
        return ResponseEntity.ok(merchantService.findAll());
    }

    @PreAuthorize("hasAuthority('FIND_MERCHANT')")
    @GetMapping("/merchants/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        LOG.info("Call Get All Merchants");
        Optional<Merchant> optional = merchantService.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('UPDATE_MERCHANT')")
    @PutMapping("/merchants/{id}")
    public ResponseEntity updateById(@PathVariable String id, @Valid @RequestBody Merchant merchant) {
        LOG.info("Call Update Merchant: " + id);
        Optional<Merchant> optional = merchantService.updateById(id, merchant);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('DELETE_MERCHANT')")
    @DeleteMapping("/merchants/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOG.info("Call Delete Merchant: " + id);
//        Optional<Merchant> optional = merchantService.findById(id);
//        if (optional.isPresent()) {
//            return new ResponseEntity(optional.get(), HttpStatus.OK);
//        }
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
