package com.tr.getir.ReadingIsGood.Repository;


import com.tr.getir.ReadingIsGood.Model.Order;
import com.tr.getir.ReadingIsGood.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<List<Order>> findByUserOrderByOrderDate (User user);

    Optional<List<Order>> findByUserAndOrderDateBetweenOrderByOrderDate(User user, Date startDate , Date endDate);
}
