package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Dto.AddOrderRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.GetLogRequestDTO;
import com.tr.getir.ReadingIsGood.Enums.ShoppingCartStatus;
import com.tr.getir.ReadingIsGood.Model.Order;
import com.tr.getir.ReadingIsGood.Model.ShoppingCart;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoppingCartService shoppingCartService;

    @Transactional
    public List<Order> addOrder (AddOrderRequestDTO requestDTO , User user){
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCartByUser(user);
        List<Order> orders;
        if(shoppingCarts.size() > 0){
            orders = new ArrayList<>();
            for (ShoppingCart shoppingCart : shoppingCarts){
                Order order = new Order();
                order.setBook(shoppingCart.getBook());
                order.setOrderDate(new Date());
                order.setUser(user);
                order.setCount(shoppingCart.getCount());
                order.setTotalAmount(shoppingCart.getCount() * shoppingCart.getBook().getPrice());
                order.setUserAddress(requestDTO.getUserAddress());
                order = orderRepository.insert(order);
                shoppingCartService.changeShoppingCartStatus(shoppingCart.getId(), ShoppingCartStatus.COMPLETED);
                orders.add(order);
            }
            return orders;
        } else {

            throw new RuntimeException("shopping cart cannot be empty");

        }

    }

    public List<Order> ordersByUser(User user){
        Optional<List<Order>> oOrder = orderRepository.findByUserOrderByOrderDate(user);

       return oOrder.isPresent() ? oOrder.get() : null;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public List<Order> ordersByUserAndDate(GetLogRequestDTO requestDTO, User user) {
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();
        if (startDate == null){
            startDate = new Date(Long.MIN_VALUE);
        }
        if (endDate == null){
            endDate= new Date();
        }

        Optional<List<Order>> oOrder = orderRepository.findByUserAndOrderDateBetweenOrderByOrderDate(user,startDate,endDate);

        return oOrder.isPresent() ? oOrder.get() : null;
    }
}
