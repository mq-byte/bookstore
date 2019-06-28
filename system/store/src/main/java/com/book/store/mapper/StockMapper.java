package com.book.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.store.pojo.Stock;
import com.book.store.vo.BookStock;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {

    @Select("SELECT * FROM book b INNER JOIN stock s on b.Book_ID = s.Book_ID WHERE s.Seller_ID =#{Seller_ID}")
    List<BookStock> getStock(int Seller_ID);

    @Select("SELECT b.*,s.*,se.Seller_Name FROM book b,stock s,seller se WHERE b.Book_ID = s.Book_ID AND s.Seller_ID = se.Seller_ID;")
    List<BookStock> getGoods();

    @Select("UPDATE stock SET Stock_Num = Stock_Num - 1 WHERE Book_ID = #{Book_ID} AND Seller_ID = #{Seller_ID}")
    int updateStock(int Book_ID,int Seller_ID);
}
