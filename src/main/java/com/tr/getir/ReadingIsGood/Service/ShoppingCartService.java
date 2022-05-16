package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Dto.AddBookShoppingCartRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.ChangeStockRequestDTO;
import com.tr.getir.ReadingIsGood.Enums.ShoppingCartStatus;
import com.tr.getir.ReadingIsGood.Model.Book;
import com.tr.getir.ReadingIsGood.Model.ShoppingCart;
import com.tr.getir.ReadingIsGood.Model.User;
import com.tr.getir.ReadingIsGood.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    BookService bookService;

    public List<ShoppingCart> getShoppingCartByUser(User user){
        return shoppingCartRepository.findByUserAndStatusOrderByAddDate(user,ShoppingCartStatus.ACTIVE).get();
    }

    @Transactional
    public ShoppingCart addBookInShoppingCart (AddBookShoppingCartRequestDTO requestDTO, User user){
        Book book = bookService.getBookById(requestDTO.getBook().getId());
        Optional<ShoppingCart> oShoppingCart = shoppingCartRepository.findByBookAndUserAndStatus(book,user,ShoppingCartStatus.ACTIVE);
        ShoppingCart shoppingCart;
        bookService.changeStock(new ChangeStockRequestDTO(book.getId(),book.getStock() - requestDTO.getCount()));
        if (oShoppingCart.isPresent()){
            shoppingCart = oShoppingCart.get();
            shoppingCart.setAddDate(new Date());
            shoppingCart.setCount(shoppingCart.getCount()+requestDTO.getCount());
            shoppingCart = shoppingCartRepository.save(shoppingCart);
        } else {
            shoppingCart = new ShoppingCart();
            shoppingCart.setBook(book);
            shoppingCart.setUser(user);
            shoppingCart.setAddDate(new Date());
            shoppingCart.setCount(requestDTO.getCount());
            shoppingCart.setStatus(ShoppingCartStatus.ACTIVE);
            shoppingCart = shoppingCartRepository.insert(shoppingCart);
              }
        return  shoppingCart;
    }

    @Transactional
    public ShoppingCart removeBookInShoppingCart (String shoppingCartId, User user){
        Optional<ShoppingCart> oShoppingCart = shoppingCartRepository.findByUserAndIdAndStatus(user,shoppingCartId,ShoppingCartStatus.ACTIVE);
        if (oShoppingCart.isPresent()){
            ShoppingCart shoppingCart = oShoppingCart.get();
            Book book = bookService.getBookById(shoppingCart.getBook().getId());
            bookService.changeStock(new ChangeStockRequestDTO(book.getId(),
                    book.getStock() + shoppingCart.getCount()));

            shoppingCart.setStatus(ShoppingCartStatus.REVOKE);
            shoppingCartRepository.save(shoppingCart);
            return  shoppingCart;
        } else {
            throw new RuntimeException("invalid request !");
        }
    }

    @Transactional
    public void removeBookTimeOut(){
        Date now = new Date();
        long time =  now.getTime() - (1000*60*60*15);
        now = new Date(time);

        Optional<List<ShoppingCart>> oShoppingCarts = shoppingCartRepository.findByStatusAndAddDateBeforeOrderByAddDate(ShoppingCartStatus.ACTIVE,now);

        if (oShoppingCarts.isPresent()){
            for (ShoppingCart shoppingCart : oShoppingCarts.get()){
                bookService.changeStock(new ChangeStockRequestDTO(shoppingCart.getBook().getId(),
                        shoppingCart.getBook().getStock() + shoppingCart.getCount()));
                shoppingCart.setStatus(ShoppingCartStatus.TIME_OUT);
                shoppingCartRepository.save(shoppingCart);
            }
        }

    }

    public ShoppingCart changeShoppingCartStatus(String shoppingCartId , ShoppingCartStatus shoppingCartStatus){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).get();
        shoppingCart.setStatus(shoppingCartStatus);
        return shoppingCartRepository.save(shoppingCart);
    }
}
