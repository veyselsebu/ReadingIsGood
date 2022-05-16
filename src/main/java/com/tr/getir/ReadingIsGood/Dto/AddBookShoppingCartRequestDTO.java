package com.tr.getir.ReadingIsGood.Dto;

import com.tr.getir.ReadingIsGood.Model.Book;

import javax.validation.constraints.Min;

public class AddBookShoppingCartRequestDTO {
    private Book book;

    @Min(value = 1,message = "must minimum 1 count book in shopping cart!")
    private int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
