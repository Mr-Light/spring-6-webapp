package learn.doomispring.services;

import learn.doomispring.domain.Book;

public interface BookService {

    public Iterable<Book> findAll();
}
