package com.book.store.controller;

import com.book.store.model.JsonResult;
import com.book.store.pojo.Buyer;
import com.book.store.service.imp.BookStockImp;
import com.book.store.service.imp.GoodsImp;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/buyer")
public class BuyContrller {

    @Resource
    GoodsImp goodsImp;

    @Resource
    BookStockImp bookStockImp;

    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    JsonResult getAllGoods(){
        return JsonResult.success(goodsImp.selectGoods());
    }

    @RequestMapping(value = "/goods/{bookId}/{sellerId}",method = RequestMethod.GET)
    JsonResult buy(@PathVariable int bookId, @PathVariable int sellerId, HttpSession httpSession, HttpServletRequest httpServletRequest){
        Cookie[] cs = httpServletRequest.getCookies();
        String str = null;
        for (Cookie c:cs){
            if("token".equals(c.getName())){
                str = c.getValue();
            }
        }

        if(str != null) {
            Buyer b = (Buyer) httpSession.getAttribute(str);
            if (b == null) {
                return JsonResult.error("登录失效");
            }else {
                goodsImp.updateStock(bookId,sellerId);
                return JsonResult.success("购买成功");
            }
        }else {
            return  JsonResult.error("登录异常");
        }


    }
}
