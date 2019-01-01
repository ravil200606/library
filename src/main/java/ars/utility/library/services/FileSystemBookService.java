package ars.utility.library.services;

import ars.utility.library.models.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemBookService implements BookService {

    @Value("${application.bookdirectory}")
    private String bookdirectory;

    @Override
    public Collection<Book> findAll() throws IOException {
        List<Book> books = new ArrayList<>();
        if (bookdirectory != null) {
            try (Stream<Path> paths = Files.walk(Paths.get(bookdirectory))) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(entry -> {
                            books.add(new Book(entry.getFileName().toString(),
                                    "download?file="+entry.toString()));
                        });
            }
        }
        return books;
    }
}
