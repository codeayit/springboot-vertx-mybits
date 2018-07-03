package com.ayit.demo.service;

import com.ayit.demo.entity.Book;
import com.ayit.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public  class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAll() {
//        Iterable<Book> all = bookRepository.findAll();
        Iterable<Book> all = new ArrayList<>();
        Book book = null;
        for (int i=0;i<5;i++){
            book = new Book();
            book.setId(0l);
            book.setName("name _ "+i);
            ((ArrayList<Book>) all).add(book);
        }
        System.out.print("books.size = "+((ArrayList<Book>) all).size());
        return StreamSupport.stream(all.spliterator(), false).collect(toList());
    }

}
