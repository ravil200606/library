package ars.utility.library.services;

import ars.utility.library.models.Book;

import java.io.IOException;
import java.util.Collection;

public interface BookService {
    Collection<Book> findAll() throws IOException;
}
