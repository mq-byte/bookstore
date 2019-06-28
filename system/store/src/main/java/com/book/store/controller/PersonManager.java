package com.book.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.store.model.JsonResult;
import com.book.store.pojo.Buyer;
import com.book.store.pojo.Seller;
import com.book.store.service.PersonManagerSev;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="/users")
public class PersonManager {

    @Resource
    private PersonManagerSev personManagerSev;

    @RequestMapping(value="/buyer/regest",method= RequestMethod.POST)
    public JsonResult regestBuyer(@RequestBody Buyer buyer){
        QueryWrapper<Buyer> qw = new QueryWrapper<Buyer>();
        qw.eq("User_Name",buyer.getUserName());
        Buyer r = personManagerSev.selectByBuyerEntity(qw);
        if(r == null){
            if(personManagerSev.insertBuyer(buyer) == 1){
                return JsonResult.success("注册成功");
            }else {
                return JsonResult.error("注册失败");
            }
        }else {
            return JsonResult.error("用户名重复");
        }


    }


    @RequestMapping(value = "/buyer/login",method = RequestMethod.POST)
    public JsonResult loginBuyer(@RequestBody Map<String,String> map, HttpSession httpSession, HttpServletResponse httpServletResponse){
        QueryWrapper<Buyer> qw = new QueryWrapper<Buyer>();
        qw.eq("User_Name",map.get("userName"));
        Buyer r = personManagerSev.selectByBuyerEntity(qw);
        if(r == null){
            return JsonResult.error("用户不存在");
        }else if (map.get("password").equals(r.getPassword())) {
            String s = UUID.randomUUID().toString();
            httpSession.setAttribute(s, r);
            Cookie c = new Cookie("token",s);
            c.setPath("/");
            c.setMaxAge(1000000);
            httpServletResponse.addCookie(c);
            return JsonResult.success("登录成功");
        }else {
            return JsonResult.error("密码错误");
        }
    }


    @RequestMapping(value="/Seller/regest",method= RequestMethod.POST)
    public JsonResult regestBuyer(@RequestBody Seller seller){
        QueryWrapper<Seller> qw = new QueryWrapper<Seller>();
        qw.eq("Seller_Name",seller.getSellerName());
        Seller s = personManagerSev.selectBySellerEntity(qw);
        if(s == null){
            if(personManagerSev.insertSeller(seller) == 1){
                return JsonResult.success("注册成功");
            }else {
                return JsonResult.error("注册失败");
            }
        }else {
            return JsonResult.error("用户名重复");
        }
    }


    @RequestMapping(value = "/Seller/login",method = RequestMethod.POST)
    public JsonResult loginSeller(@RequestBody Map<String,String> map, HttpSession httpSession, HttpServletResponse httpServletResponse){
        QueryWrapper<Seller> qw = new QueryWrapper<Seller>();
        qw.eq("Seller_Name",map.get("sellerName"));
        Seller s = personManagerSev.selectBySellerEntity(qw);
        if(s == null){
            return JsonResult.error("用户不存在");
        }else if (map.get("password").equals(s.getPassword())) {
            String str = UUID.randomUUID().toString();
            httpSession.setAttribute(str, s);
            Cookie c = new Cookie("token",str);
            c.setPath("/");
            c.setMaxAge(1000000);
            httpServletResponse.addCookie(c);
            return JsonResult.success("登录成功");
        }else {
            return JsonResult.error("密码错误");
        }
    }

}
