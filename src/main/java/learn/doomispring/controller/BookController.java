package learn.doomispring.controller;

import learn.doomispring.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){

        // an attribute for the get request
        model.addAttribute("bookAttribute", bookService.findAll());

        // has to be a name of a view (html template)
        return "books";
    }
}
