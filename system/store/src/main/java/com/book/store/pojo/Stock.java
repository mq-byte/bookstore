package com.book.store.pojo;

import lombok.Data;

@Data
public class Stock {
    private int bookId;
    private int sellerId;
    private int stockNum;
    private float price;
}
