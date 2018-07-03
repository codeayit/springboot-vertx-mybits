package com.ayit.demo.handler;

import com.ayit.demo.entity.Book;
import com.ayit.demo.service.BookAsyncService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import com.ayit.demo.common.*;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class BookHandler {

    public static void addBook(RoutingContext routingContext, BookAsyncService bookAsyncService){


//        Book book = new Book(routingContext.getBodyAsJson());
        Book book = new Book();
        book.setId(1l);
        book.setName("十万个为什么");

        bookAsyncService.add(book, new Handler<AsyncResult<Book>>() {
            @Override
            public void handle(AsyncResult<Book> bookAsyncResult) {
                System.out.print("BookHandler.addBook \n");
                if (bookAsyncResult.succeeded()) {
                    routingContext.response().setStatusCode(HTTP_CREATED).end();
                } else {
                    routingContext.fail(bookAsyncResult.cause());
                }
            }
        });
    }


    public static void getAllBooks(RoutingContext routingContext, BookAsyncService bookAsyncService){

        bookAsyncService.getAll(new Handler<AsyncResult<List<Book>>>() {
            @Override
            public void handle(AsyncResult<List<Book>> listAsyncResult) {
                System.out.print("BookHandler.getAllBooks \n");
                List<Book> result = listAsyncResult.result();
                JsonArray jsonArray = new JsonArray(result);
                HttpUtils.fireJsonResponse(routingContext.response(), HTTP_OK, jsonArray.encodePrettily());
            }
        });
    }

}
