package com.book.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.store.model.JsonResult;
import com.book.store.pojo.Book;
import com.book.store.pojo.Seller;
import com.book.store.pojo.Stock;
import com.book.store.service.imp.BookStockImp;
import com.book.store.vo.BookStock;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/book")
public class BookStockContrller {

    @Resource
    BookStockImp bookStockImp;

    @RequestMapping(value = "/stock",method = RequestMethod.GET)
    JsonResult findStock(HttpSession httpSession, HttpServletRequest httpServletRequest){
        Cookie[] cs = httpServletRequest.getCookies();
        String str = null;
        for (Cookie c:cs){
            if("token".equals(c.getName())){
                str = c.getValue();
            }
        }

        if(str == null){
            return JsonResult.error("登录异常");
        }else {
            Seller s = (Seller)httpSession.getAttribute(str);
            if(s == null){
                return JsonResult.error("登录失效");
            }else {
                return JsonResult.success(bookStockImp.selectListById(s.getSellerId()));
            }
        }
    }

    @RequestMapping(value = "/stock",method = RequestMethod.POST)
    JsonResult insertStock(@RequestBody BookStock bookStock, HttpSession httpSession, HttpServletRequest httpServletRequest){
        Cookie[] cs = httpServletRequest.getCookies();
        String str = null;
        for (Cookie c:cs){
            if("token".equals(c.getName())){
                str = c.getValue();
            }
        }

        if(str != null){
            Seller s = (Seller)httpSession.getAttribute(str);
            if(s == null){
                return JsonResult.error("登录失效");
            }
            QueryWrapper<Book> qw = new QueryWrapper<Book>();
            qw.eq("Book_Name",bookStock.getBookName());
            Book isb = bookStockImp.selectBook(qw);

            if(isb == null){
                Book b = new Book();
                b.setBookName(bookStock.getBookName());
                b.setComment(bookStock.getComment());
                b.setPress(bookStock.getPress());
                b.setPublishYear(bookStock.getPublishYear());
                b.setWriter(bookStock.getWriter());
                bookStockImp.insertBook(b);
                QueryWrapper<Book> qw2 = new QueryWrapper<Book>();
                qw2.eq("Book_Name",bookStock.getBookName());
                Book isb2 = bookStockImp.selectBook(qw2);

                Stock stock = new Stock();
                stock.setBookId(isb2.getBookId());
                stock.setSellerId(s.getSellerId());
                stock.setStockNum(bookStock.getStockNum());
                stock.setPrice(bookStock.getPrice());
                bookStockImp.insertStock(stock);
                return JsonResult.success("添加成功");
            }else {
                QueryWrapper<Stock> qw2 = new QueryWrapper<Stock>();
                Map<String,Object> mp = new HashMap<String, Object>();
                mp.put("Book_ID",isb.getBookId());
                mp.put("Seller_ID",s.getSellerId());
                qw2.allEq(mp);
                Stock stock = bookStockImp.selectStock(qw2);
                if(stock == null){
                    Stock stock2 = new Stock();
                    stock2.setBookId(bookStock.getBookId());
                    stock2.setSellerId(s.getSellerId());
                    stock2.setStockNum(bookStock.getStockNum());
                    stock2.setPrice(bookStock.getPrice());
                    bookStockImp.insertStock(stock2);
                    return JsonResult.success("添加成功");
                }else {
                    return JsonResult.error("不能重复添加");
                }
            }

        }else {
            return JsonResult.error("登录异常");
        }
    }

    @RequestMapping(value = "/stock",method = RequestMethod.PUT)
    JsonResult updateStock(@RequestBody Stock stock,HttpServletRequest httpServletRequest,HttpSession httpSession){
        Cookie[] cs = httpServletRequest.getCookies();
        String str = null;
        for (Cookie c:cs){
            if("token".equals(c.getName())){
                str = c.getValue();
            }
        }

        if(str == null){
            return JsonResult.error("登录异常");
        }else {
            Seller s = (Seller)httpSession.getAttribute(str);
            QueryWrapper<Stock> qw = new QueryWrapper<Stock>();
            Map<String,Integer> map = new HashMap<String,Integer>();
            map.put("Seller_ID",s.getSellerId());
            map.put("Book_ID",stock.getBookId());
            qw.allEq(map);
            bookStockImp.updateStock(stock,qw);
            return JsonResult.success("修改成功");
        }


    }

    @RequestMapping(value = "/stock/{bookId}",method = RequestMethod.DELETE)
    JsonResult deletStock(@PathVariable int bookId,HttpServletRequest httpServletRequest,HttpSession httpSession){
        Cookie[] cs = httpServletRequest.getCookies();
        String str = null;
        for (Cookie c:cs){
            if("token".equals(c.getName())){
                str = c.getValue();
            }
        }

        if(str == null){
            return JsonResult.error("登录异常");
        }else {
            Seller s = (Seller)httpSession.getAttribute(str);
            QueryWrapper<Stock> qw = new QueryWrapper<Stock>();
            Map<String,Integer> map = new HashMap<String,Integer>();
            map.put("Seller_ID",s.getSellerId());
            map.put("Book_ID",bookId);
            qw.allEq(map);
            bookStockImp.deletStock(qw);
            return JsonResult.success("修改成功");
        }
    }

}
