package com.tr.getir.ReadingIsGood.Dto;

import com.tr.getir.ReadingIsGood.Model.UserAddress;

import javax.validation.constraints.NotNull;

public class AddOrderRequestDTO {

    @NotNull(message = "address required")
    private UserAddress userAddress;

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
