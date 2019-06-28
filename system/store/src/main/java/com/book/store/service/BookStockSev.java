package com.book.store.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.book.store.pojo.Book;
import com.book.store.pojo.Stock;
import com.book.store.vo.BookStock;

import java.util.List;


public interface BookStockSev {

    int insertBook(Book book);

    int insertStock(Stock stock);

    Book selectBook(Wrapper<Book> wrapper);

    Stock selectStock(Wrapper<Stock> wrapper);

    List<BookStock> selectListById(int id);

    int updateStock(Stock stock,Wrapper<Stock> wrapper);

    int deletStock(Wrapper<Stock> wrapper);
}
