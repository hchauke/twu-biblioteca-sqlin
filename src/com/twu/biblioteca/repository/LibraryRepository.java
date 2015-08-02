package com.twu.biblioteca.repository;

import java.util.HashMap;
import java.util.Map;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Movie;

public class LibraryRepository {

    private static Map<String, Book> books = new HashMap<String, Book>();
    private static Map<String, Movie> movies = new HashMap<String, Movie>();
    static {
        books.put("B0001", new Book("B0001", "Head First Java", "Kathy Sierra & Bates", "2005", "O'Reilly Media, Inc"));
        books.put("B0002", new Book("B0002", "Test Driven Development", "Kent Beck", "2002", "Addison-Wesley Professional"));
        books.put("B0003", new Book("B0003", "Refactoring", "Martin Fowler & Kent Beck et al", "1999", "Addison-Wesley Professional"));

        movies.put("M0001", new Movie("M0001", "The Runner", "2015", "Austin Stark", 8.5));
        movies.put("M0002", new Movie("M0002", "The Avengers", "2012", "Joss Whedon", 9));
    }

    private static Map<String, String> checkedBooks = new HashMap<String, String>();
    private static Map<String, String> checkedMovies = new HashMap<String, String>();

    public static Map<String, Book> listBooks() {
        return books;
    }

    public static void saveCheckoutBook(String bookId, String readerId) {
        checkedBooks.put(bookId, readerId);
    }

    public static Map<String, String> getCheckedBooks() {
        return checkedBooks;
    }

    public static void returnCheckedBook(String bookId) {
        checkedBooks.remove(bookId);
    }

    public static Map<String, Movie> listMovies() {
        return movies;
    }

    public static Map<String, String> getCheckedMovies() {
        return checkedMovies;
    }

    public static void saveCheckoutMovie(String movieId, String readerId) {
        checkedMovies.put(movieId, readerId);
    }

    public static void returnCheckedMovie(String movieId) {
        checkedMovies.remove(movieId);
    }
}
