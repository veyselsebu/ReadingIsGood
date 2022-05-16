package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.UserCreateRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.UserResponseDTO;
import com.tr.getir.ReadingIsGood.Enums.Role;
import com.tr.getir.ReadingIsGood.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping()
    public UserResponseDTO userCreate(@Valid  @RequestBody UserCreateRequestDTO requestDTO) {
        return userService.userCreate(requestDTO, Role.CUSTOMER);
    }
}
