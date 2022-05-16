package com.tr.getir.ReadingIsGood.Controller;


import com.tr.getir.ReadingIsGood.Config.JwtUtil;
import com.tr.getir.ReadingIsGood.Dto.AddAddressRequestDTO;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Model.UserAddress;
import com.tr.getir.ReadingIsGood.Service.UserAddressService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user-address")
public class UserAdressController {
    @Autowired
    UserAddressService userAddressService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping
    public UserAddress addAddress(@RequestBody AddAddressRequestDTO requestDTO, HttpServletRequest httpServletRequest){
        return userAddressService.addAddress(requestDTO,userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping("/{id}")
    public UserAddress removeAddress (@PathVariable String id,HttpServletRequest httpServletRequest){
    return userAddressService.removeAddress(id,userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping
    public List<UserAddress> getAdressListByUser( HttpServletRequest httpServletRequest){
        return userAddressService.getAdressListByUser(userService.getTokenToUser(httpServletRequest));
    }

}
