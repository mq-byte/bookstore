package com.book.store.service.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.book.store.mapper.BookMapper;
import com.book.store.mapper.StockMapper;
import com.book.store.pojo.Book;
import com.book.store.pojo.Stock;
import com.book.store.service.BookStockSev;
import com.book.store.vo.BookStock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookStockImp implements BookStockSev {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private StockMapper stockMapper;

    @Override
    public int insertBook(Book book) {
        return bookMapper.insert(book);
    }


    @Override
    public int insertStock(Stock stock) {
        return stockMapper.insert(stock);
    }

    @Override
    public Book selectBook(Wrapper<Book> wrapper) {
        return bookMapper.selectOne(wrapper);
    }

    @Override
    public Stock selectStock(Wrapper<Stock> wrapper) {
        return stockMapper.selectOne(wrapper);
    }

    @Override
    public List<BookStock> selectListById(int id) {
        return stockMapper.getStock(id);
    }

    @Override
    public int updateStock(Stock stock,Wrapper<Stock> wrapper) {
        return stockMapper.update(stock,wrapper);
    }

    @Override
    public int deletStock(Wrapper<Stock> wrapper) {
        return stockMapper.delete(wrapper);
    }
}
