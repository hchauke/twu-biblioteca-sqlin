package com.twu.biblioteca.service;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Menu;
import com.twu.biblioteca.enumeration.Action;

public class ConsoleServiceTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ConsoleService consoleService;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        consoleService = new ConsoleService();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void should_be_able_to_show_welcome_message() {
        consoleService.showWelcome();
        assertEquals(outContent.toString(), "===============Welcome to the Biblioteca!===============\n\n");
    }

    @Test
    public void should_be_able_to_print_specified_error_message() {
        String message = "This is an error message.";
        consoleService.printError(message);
        assertEquals(errContent.toString(), message + "\n\n");
    }

    @Test
    public void should_be_able_to_print_specified_message() throws Exception {
        String message = "This is a message.";
        consoleService.printMessage(message);
        assertEquals(outContent.toString(), message + "\n\n");
    }

    @Test
    public void should_be_able_to_show_tips_for_choosing_option() throws IOException {
        consoleService.getBufferedReader().close();
        int option = consoleService.chooseOption();
        assertEquals(outContent.toString(), "Please choose an option: ");
        assertEquals(option, 0);
    }

    @Test
    public void should_be_able_to_print_menu_prompt() {
        List<Menu> menus = new ArrayList<Menu>(Arrays.asList(
                new Menu("List Books", Action.LIST_ITEMS),
                new Menu("Checkout Book", Action.CHECKOUT_ITEM),
                new Menu("Quit", Action.QUIT)
        ));
        consoleService.printMenuPrompt(menus);
        assertEquals(outContent.toString(), "\n1.List Books      2.Checkout Book      3.Quit\n");
    }

    @Test
    public void should_be_able_to_print_book_list() throws Exception {
        List<Book> books = new ArrayList<Book>(Arrays.asList(
                new Book("123", "hello", "water", "2015", "press"),
                new Book("456", "haha", "lin", "2015", "bbc")
        ));
        consoleService.printBookList(books);
        String result = "Book ID         Title         Author     Published Year      Press\n" +
                "------------------------------------------------------------------\n" +
                "123    hello    water    2015    press\n" +
                "456    haha    lin    2015    bbc\n";
        assertEquals(outContent.toString(), result);
    }
}
