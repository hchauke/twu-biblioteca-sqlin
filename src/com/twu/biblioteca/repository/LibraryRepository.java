package com.twu.biblioteca.repository;

import java.util.HashMap;
import java.util.Map;

import com.twu.biblioteca.domain.Book;

public class LibraryRepository {

    private static Map<String, Book> books = new HashMap<String, Book>(){
        {
            put("B0001", new Book("B0001", "Head First Java", "Kathy Sierra & Bates", "2005", "O'Reilly Media, Inc"));
            put("B0002", new Book("B0002", "Test Driven Development: By Example", "Kent Beck", "2002", "Addison-Wesley Professional"));
        }
    };
    private static Map<String, String> checkoutBooks = new HashMap<String, String>();

    public static Map<String, Book> listBooks() {
        return books;
    }

    public static void saveCheckoutBook(String bookId, String readerId) {
        checkoutBooks.put(bookId, readerId);
    }

    public static Map<String, String> getCheckoutBooks() {
        return checkoutBooks;
    }
}
