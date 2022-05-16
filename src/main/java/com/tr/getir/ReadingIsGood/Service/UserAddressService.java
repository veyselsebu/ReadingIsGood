package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Dto.AddAddressRequestDTO;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Model.UserAddress;
import com.tr.getir.ReadingIsGood.Repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressService {
    @Autowired
    UserAddressRepository userAddressRepository;


    public UserAddress addAddress(AddAddressRequestDTO requestDTO, User user){
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setAddressDetail(requestDTO.getAddressDetail());
        userAddress.setCity(requestDTO.getCity());
        userAddress.setCountry(requestDTO.getCountry());

        return userAddressRepository.insert(userAddress);
    }

    public UserAddress removeAddress (String id,User user){
        Optional<UserAddress> address = userAddressRepository.findByUserAndId(user,id);
        if (address.isPresent()){
           userAddressRepository.delete(address.get());
           return address.get();
        } {
            throw new RuntimeException("You are not authorized to delete the specified address.");
        }
    }

    public List<UserAddress> getAdressListByUser(User user){
        return userAddressRepository.findByUser(user).get();
    }





}
