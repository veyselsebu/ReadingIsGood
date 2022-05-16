package com.tr.getir.ReadingIsGood.Repository;

import com.tr.getir.ReadingIsGood.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmailAndPassword(String email , String password);
    Optional<User> findByEmail(String email);
}
