package com.tr.getir.ReadingIsGood.Repository;

import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Model.UserAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends MongoRepository<UserAddress,String> {

    Optional<List<UserAddress>> findByUser(User user);
    Optional<UserAddress> findByUserAndId(User user,String id);
}
