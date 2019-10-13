package com.op.merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    List<Merchant> findAll();

    Optional<Merchant> findById(String id);

    Merchant create(Merchant merchant);

    Optional<Merchant> updateById(String id, Merchant merchant);
}
