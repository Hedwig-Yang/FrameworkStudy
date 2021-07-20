package com.atguigu.mybatis.beans;

/**
 * @Author:KUN
 * @Data:2021/7/20 10:19
 * @Description: 书
 * @Version:1.0
 */
public class Book {
    private String isbn;
    private String bookName;
    private String price;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
