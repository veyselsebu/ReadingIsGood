package com.tr.getir.ReadingIsGood.Batch;

import com.tr.getir.ReadingIsGood.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartTimeOutController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Scheduled(fixedRate = 1000)
    private void controller(){
        shoppingCartService.removeBookTimeOut();
    }

}
