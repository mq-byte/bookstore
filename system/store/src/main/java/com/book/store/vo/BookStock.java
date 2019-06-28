package com.book.store.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BookStock {
    private int sellerId;
    private String sellerName;
    private int bookId;
    private String bookName;
    private Date publishYear;
    private String writer;
    private String press;
    private String comment;
    private float price;
    private int stockNum;
}
