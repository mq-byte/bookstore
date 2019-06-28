package com.book.store.service.imp;

import com.book.store.mapper.StockMapper;
import com.book.store.service.GoodsSev;
import com.book.store.vo.BookStock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsImp implements GoodsSev {

    @Resource
    StockMapper stockMapper;

    @Override
    public List<BookStock> selectGoods() {
        return stockMapper.getGoods();
    }

    @Override
    public int updateStock(int bookId, int sellerId) {
        return stockMapper.updateStock(bookId,sellerId);
    }
}
