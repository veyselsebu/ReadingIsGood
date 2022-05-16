package com.tr.getir.ReadingIsGood.Service;


import com.tr.getir.ReadingIsGood.Dto.AddBookRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.ChangeStockRequestDTO;
import com.tr.getir.ReadingIsGood.Model.Book;
import com.tr.getir.ReadingIsGood.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book addBook(AddBookRequestDTO requestDTO){
        Book book = new Book();

        book.setName(requestDTO.getName());
        book.setPrice(requestDTO.getPrice());
        book.setType(requestDTO.getType());
        book.setStock(requestDTO.getStock());
        book.setWriter(requestDTO.getWriter());
        book.setYear(requestDTO.getYear());

        return bookRepository.insert(book);
    }

    public List<Book> getBooks(){
        return  bookRepository.findAll();
    }

    public Book changeStock(ChangeStockRequestDTO changeStockRequestDTO){
       if (changeStockRequestDTO.getNewStock() >= 0){
        Optional<Book> oBook = bookRepository.findById(changeStockRequestDTO.getBookId());

        if (oBook.isPresent()){
            Book book = oBook.get();
            book.setStock(changeStockRequestDTO.getNewStock());
            return bookRepository.save(book);
        } else {
            throw  new RuntimeException("book not found");
        }} else {
           throw  new RuntimeException("Insufficient Stock !");
       }
    }

    public Book getBookById(String id){
        Optional<Book> oBook = bookRepository.findById(id);
        if (oBook.isPresent()){
            return oBook.get();
        } else {
            throw new RuntimeException("Invlid BookId !");
        }
    }


}
