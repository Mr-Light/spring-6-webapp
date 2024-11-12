package learn.doomispring.services;

import learn.doomispring.domain.Author;

public interface AuthorService {
    public Iterable<Author> findAll();
}
