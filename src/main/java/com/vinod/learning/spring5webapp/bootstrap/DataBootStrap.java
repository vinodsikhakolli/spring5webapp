package com.vinod.learning.spring5webapp.bootstrap;

import com.vinod.learning.spring5webapp.model.Author;
import com.vinod.learning.spring5webapp.model.Book;
import com.vinod.learning.spring5webapp.model.Publisher;
import com.vinod.learning.spring5webapp.repositories.AuthorRepository;
import com.vinod.learning.spring5webapp.repositories.BookRepository;
import com.vinod.learning.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DataBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author author1 = new Author("Vinod Kumar", "Sikhakolli");
        Book b1 = new Book("Art of Living", "isbn123098");
        Publisher p1 = new Publisher("S Chand", "Hyderabad");
        b1.setPublisher(p1);
        author1.getBooks().add(b1);
        b1.getAuthors().add(author1);

        publisherRepository.save(p1);
        authorRepository.save(author1);
        bookRepository.save(b1);


        Author author2 = new Author("Ramanujan", "Rangachari");
        Book b2 = new Book("World of Statistics", "isbn1586098");
        Publisher p2 = new Publisher("S Chand", "Bangalore");
        b2.setPublisher(p2);
        author2.getBooks().add(b2);
        b2.getAuthors().add(author2);
        publisherRepository.save(p2);
        authorRepository.save(author2);
        bookRepository.save(b2);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
