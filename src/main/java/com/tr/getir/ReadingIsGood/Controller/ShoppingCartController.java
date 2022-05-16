package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.AddBookShoppingCartRequestDTO;
import com.tr.getir.ReadingIsGood.Model.ShoppingCart;
import com.tr.getir.ReadingIsGood.Service.ShoppingCartService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping
    public ShoppingCart addBookInShoppingCart(@Valid @RequestBody AddBookShoppingCartRequestDTO requestDTO, HttpServletRequest httpServletRequest){
        return shoppingCartService.addBookInShoppingCart(requestDTO,userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping
    public List<ShoppingCart> getShoppingCartsByUser(HttpServletRequest httpServletRequest){
        return shoppingCartService.getShoppingCartByUser(userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping("/{shoppingCartId}")
    public ShoppingCart removeShoppingCart (@Valid @PathVariable String shoppingCartId , HttpServletRequest httpServletRequest){
        return shoppingCartService.removeBookInShoppingCart(shoppingCartId,userService.getTokenToUser(httpServletRequest));
    }

}
