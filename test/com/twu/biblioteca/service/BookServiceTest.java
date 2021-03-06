package com.twu.biblioteca.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.twu.biblioteca.domain.Book;

public class BookServiceTest {

    private ItemService bookService;

    @Before
    public void setUp() {
        bookService = new BookService();
    }

    @Test
    public void should_be_able_to_get_book_list() {
        List<Book> bookList = bookService.listItems();

        assertFalse(bookList.isEmpty());
        Book book = bookList.get(0);
        assertEquals(book.getId(), "B0001");
        assertEquals(book.getTitle(), "Head First Java");
        assertEquals(book.getAuthor(), "Kathy Sierra & Bates");
        assertEquals(book.getPublishedYear(), "2005");
        assertEquals(book.getPress(), "O'Reilly Media, Inc");
    }

    @Test
    public void should_be_able_to_checkout_book() {
        String message = bookService.checkoutItem("B0003", "111-1111");
        assertEquals(message, "Thank you! Enjoy the book");
    }

    @Test
    public void should_not_checkout_book_when_book_is_already_checked_out() {
        bookService.checkoutItem("B0001", "111-1111");
        String message = bookService.checkoutItem("B0001", "333-3333");
        assertEquals(message, "That book is not available.");
    }

    @Test
    public void should_not_checkout_book_when_book_not_exists() {
        String message = bookService.checkoutItem("B000X", "111-1111");
        assertEquals(message, "That book is not available.");
    }

    @Test
    public void should_be_able_to_return_book() {
        bookService.checkoutItem("B0001", "111-1111");
        String message = bookService.returnCheckedItem("B0001", "111-1111");
        assertEquals(message, "Thank you for returning the book.");
    }

    @Test
    public void should_not_return_book_when_book_id_is_incorrect() {
        String message = bookService.returnCheckedItem("B000X", "111-1111");
        assertEquals(message, "That is not a valid book to return.");
    }

    @Test
    public void should_not_return_book_when_reader_id_is_incorrect() {
        bookService.checkoutItem("B0001", "111-1111");
        String message = bookService.returnCheckedItem("B0001", "XXX-XXXX");
        assertEquals(message, "That is not a valid book to return.");
    }

    @Test
    public void should_be_able_to_generate_book_column_header() {
        assertEquals(bookService.generateItemColumnHeader(), Book.getColumnHeader());
    }

    @Test
    public void should_be_able_to_generate_book_column_content() {
        Book book = new Book("id", "title", "author", "2015", "press");
        assertEquals(bookService.generateItemColumnContent(book), book.getColumnContent());
    }

    @Test
    public void should_be_able_to_list_checked_books_and_its_readers() {
        List<String> checkedBooks = bookService.listCheckedItems();

        assertFalse(checkedBooks.isEmpty());
        assertTrue(checkedBooks.contains("Book: Test Driven Development is checked by Wrongkey"));
    }
}
