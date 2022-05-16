package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.LoginRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.LoginResponsetDTO;
import com.tr.getir.ReadingIsGood.Dto.UserCreateRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.UserResponseDTO;
import com.tr.getir.ReadingIsGood.Enums.Role;
import com.tr.getir.ReadingIsGood.Service.AuthenticationService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;


    @PostMapping
    public LoginResponsetDTO getToken(@RequestBody LoginRequestDTO requestDTO){
        return authenticationService.getToken(requestDTO);
    }

    @PostMapping("/new-account")
    @Valid
    public UserResponseDTO newAccount(@Valid @RequestBody UserCreateRequestDTO requestDTO){
        return userService.userCreate(requestDTO, Role.CUSTOMER);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/new-admin-account")
    public UserResponseDTO newAdminAccount(@Valid @RequestBody UserCreateRequestDTO requestDTO){
        return userService.userCreate(requestDTO,Role.ADMIN);
    }


}
