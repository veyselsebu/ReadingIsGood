package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.AddOrderRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.GetLogRequestDTO;
import com.tr.getir.ReadingIsGood.Model.Order;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Service.OrderService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping
    public List<Order> addOrder(@RequestBody AddOrderRequestDTO requestDTO, HttpServletRequest httpServletRequest){
        return orderService.addOrder(requestDTO,userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping
    public List<Order> getOrders(HttpServletRequest httpServletRequest){
        return orderService.ordersByUser(userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/by-date")
    public List<Order> getOrdersByDate(@RequestBody GetLogRequestDTO requestDTO, HttpServletRequest httpServletRequest){
        return orderService.ordersByUserAndDate(requestDTO,userService.getTokenToUser(httpServletRequest));
    }
}
