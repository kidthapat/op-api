package com.op.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(String id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant create(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Optional<Merchant> updateById(String id, Merchant merchant) {
        Optional<Merchant> optional = merchantRepository.findById(id);
        if (optional.isPresent()) {
            Merchant existedMerchant = optional.get();

            existedMerchant.setEmail(merchant.getEmail());

            merchantRepository.save(existedMerchant);
        }
        return optional;
    }
}
