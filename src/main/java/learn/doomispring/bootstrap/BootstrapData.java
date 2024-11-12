package learn.doomispring.bootstrap;

import learn.doomispring.domain.Author;
import learn.doomispring.domain.Book;
import learn.doomispring.domain.Publisher;
import learn.doomispring.repositories.AuthorRepository;
import learn.doomispring.repositories.BookRepository;
import learn.doomispring.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Since we are using an inmemory database data will always need to be
// populated at every start of the application
// this class create the data needed
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // creating author
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        // creating book
        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        // saving instances
        authorRepository.save(eric);
        bookRepository.save(ddd);


        //repeat
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        // adding and saving books to author and vice versa
        // using the Saved objects returned from repository
        eric.getBooks().add(ddd);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        authorRepository.save(rod);

        // Creating and saving publisher
        Publisher pb = new Publisher();
        pb.setName("Penguin Publishing");
        pb.setAddress("123 Cairo");
        publisherRepository.save(pb);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

        // Checking publisher saved
        System.out.println("Publisher count: " + publisherRepository.count());

        // testing saving once with repository result once no
        noEJB.setPublisher(pb);
        ddd.setPublisher(pb);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        authorRepository.save(eric);
        authorRepository.save(rod);

        // note everytime save is called db is updated
        // so technically could do it once at the end





    }
}
