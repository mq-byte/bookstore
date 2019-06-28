package com.book.store.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.book.store.pojo.Buyer;
import com.book.store.pojo.Seller;

public interface PersonManagerSev {
    int insertBuyer(Buyer buyer);

    Buyer selectByBuyerEntity(Wrapper<Buyer> queryWrapper);

    int insertSeller(Seller seller);

    Seller selectBySellerEntity(Wrapper<Seller> queryWrapper);
}
