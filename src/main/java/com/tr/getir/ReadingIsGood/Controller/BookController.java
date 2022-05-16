package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.AddBookRequestDTO;
import com.tr.getir.ReadingIsGood.Dto.ChangeStockRequestDTO;
import com.tr.getir.ReadingIsGood.Model.Book;
import com.tr.getir.ReadingIsGood.Service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;

    @ApiOperation(value = "", notes = "Book types =  NOVEL,POEM,HISTORY,SCIENCE,EDUCATION,PERSONALITY,RELIGION,POLITICS", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/add")
    public Book addBook (@RequestBody AddBookRequestDTO requestDTO){
        return bookService.addBook(requestDTO);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/change-stock")
    public Book changeStock(@RequestBody ChangeStockRequestDTO requestDTO){
        return bookService.changeStock(requestDTO);
    }
    @GetMapping("/get")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
}
