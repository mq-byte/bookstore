package com.book.store.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private int bookId;
    private String bookName;
    private Date publishYear;
    private String Writer;
    private String Press;
    private String Comment;
}
