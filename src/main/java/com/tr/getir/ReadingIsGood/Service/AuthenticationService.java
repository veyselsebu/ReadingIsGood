package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Config.JwtUtil;
import com.tr.getir.ReadingIsGood.Dto.LoginRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.LoginResponsetDTO;
import com.tr.getir.ReadingIsGood.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    public LoginResponsetDTO getToken(LoginRequestDTO requestDTO){
        User user = userService.findUserEmailAndPassword(requestDTO.getEmail(),requestDTO.getPassword());
        LoginResponsetDTO responsetDTO = new LoginResponsetDTO();
        if (user != null){
            responsetDTO.setId(user.getId());
            responsetDTO.setName(user.getName());
            responsetDTO.setSurname(user.getSurname());
            responsetDTO.setToken(jwtUtil.genarateToken(user.getId()));

            return responsetDTO;
        } else {
            throw new IllegalStateException("Email or password is not valid !");
        }
    }
}
