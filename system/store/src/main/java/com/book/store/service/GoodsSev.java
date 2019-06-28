package com.book.store.service;

import com.book.store.vo.BookStock;

import java.util.List;

public interface GoodsSev {
    List<BookStock> selectGoods();

    int updateStock(int bookId,int sellerId);
}
