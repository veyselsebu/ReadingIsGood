package com.tr.getir.ReadingIsGood.Dto;

import javax.validation.constraints.Min;

public class ChangeStockRequestDTO {
    private String bookId;

    @Min(value = 0,message = "insufficient stock !")
    private int newStock;

    public ChangeStockRequestDTO() {
    }

    public ChangeStockRequestDTO(String bookId, int newStock) {
        this.bookId = bookId;
        this.newStock = newStock;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getNewStock() {
        return newStock;
    }

    public void setNewStock(int newStock) {
        this.newStock = newStock;
    }
}
