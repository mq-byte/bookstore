package com.book.store.service.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.book.store.mapper.BuyerMapper;
import com.book.store.mapper.SellerMapper;
import com.book.store.pojo.Buyer;
import com.book.store.pojo.Seller;
import com.book.store.service.PersonManagerSev;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonManagerImp implements PersonManagerSev {

    @Resource
    private BuyerMapper buyerMapper;

    @Resource
    private SellerMapper sellerMapper;

    @Override
    public int insertBuyer(Buyer buyer) {
        return buyerMapper.insert(buyer);
    }

    @Override
    public Buyer selectByBuyerEntity(Wrapper<Buyer> queryWrapper) {
        return this.buyerMapper.selectOne(queryWrapper);
    }


    @Override
    public int insertSeller(Seller seller) {
        return this.sellerMapper.insert(seller);
    }

    @Override
    public Seller selectBySellerEntity(Wrapper<Seller> queryWrapper) {
        return this.sellerMapper.selectOne(queryWrapper);
    }
}
