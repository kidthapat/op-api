package com.op.merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> findAll();

    Merchant create(Merchant merchant);
}
