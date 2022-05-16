package com.tr.getir.ReadingIsGood.Service;


import com.google.common.net.HttpHeaders;
import com.tr.getir.ReadingIsGood.Config.JwtUtil;
import com.tr.getir.ReadingIsGood.Dto.UserCreateRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.UserResponseDTO;
import com.tr.getir.ReadingIsGood.Enums.Role;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    public UserResponseDTO userCreate (UserCreateRequestDTO requestDTO,Role role){

        if (!userRepository.findByEmail(requestDTO.getEmail()).isPresent()){

        User user = new User();
        user.setCreDate(new Date());
        user.setEmail(requestDTO.getEmail());
        user.setName(requestDTO.getName());
        user.setSurname(requestDTO.getSurname());
        user.setPassword(requestDTO.getPassword());
        user.setRole(role);

        user = userRepository.insert(user);

        UserResponseDTO responseDTO = new UserResponseDTO(user.getName(),user.getSurname(),user.getCreDate()
                ,user.getId(),user.getEmail());

        return responseDTO;
        } else {
            throw new RuntimeException("All ready email address");
        }

    }

    public User findUserEmailAndPassword(String email,String password){
        Optional<User> user =userRepository.findByEmailAndPassword(email,password);
        return user.isPresent() ? user.get() : null;
    }

    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    public User getTokenToUser (HttpServletRequest httpServletRequest){
        String userId = jwtUtil.token2UserId(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findById(userId).get();
    }

    @Bean
    private void createAdmin(){
        if (!userRepository.findByEmail("admin@readingisgood.com").isPresent()){
            User user = new User();
            user.setRole(Role.ADMIN);
            user.setPassword("123456");
            user.setName("Admin");
            user.setSurname("Admin");
            user.setEmail("admin@readingisgood.com");
            user.setCreDate(new Date());

            userRepository.insert(user);
        }
    }
}
