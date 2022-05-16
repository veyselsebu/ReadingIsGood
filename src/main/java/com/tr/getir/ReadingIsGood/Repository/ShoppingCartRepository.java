package com.tr.getir.ReadingIsGood.Repository;


import com.tr.getir.ReadingIsGood.Enums.ShoppingCartStatus;
import com.tr.getir.ReadingIsGood.Model.Book;
import com.tr.getir.ReadingIsGood.Model.ShoppingCart;
import com.tr.getir.ReadingIsGood.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {

    Optional<ShoppingCart> findByBookAndUserAndStatus(Book book , User user,ShoppingCartStatus shoppingCartStatus);

    Optional <ShoppingCart> findByUserAndIdAndStatus (User user,String id,ShoppingCartStatus status);

    Optional<List<ShoppingCart>> findByUserAndStatusOrderByAddDate(User user, ShoppingCartStatus status);

    Optional<List<ShoppingCart>> findByStatusAndAddDateBeforeOrderByAddDate(ShoppingCartStatus status ,Date date);
}
