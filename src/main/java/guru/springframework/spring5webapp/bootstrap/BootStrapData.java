package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repo.AuthorRepository;
import guru.springframework.spring5webapp.repo.BookRepository;
import guru.springframework.spring5webapp.repo.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in BootStrap ");

		Publisher publisher = new Publisher();
		publisher.setName("SFG Publishing");
		publisher.setCity("Richmond");
		publisher.setState("Virginia");
		publisher.setZip("23233");
		publisher.setAddressLine1("3401, Grove Gate ");

		publisherRepository.save(publisher);
		System.out.println("Publisher Count: " + publisherRepository.count());

		Author ericauthor = new Author("Eric", "Evans");
		Book ericbook = new Book("Domain Driven Desing", "12345");
		ericauthor.getBooks().add(ericbook);
		ericbook.getAuthors().add(ericauthor);
		ericbook.setPublisher(publisher);
		publisher.getBooks().add(ericbook);

		authorRepository.save(ericauthor);
		bookRepository.save(ericbook);
		publisherRepository.save(publisher);

		Author rodauthor = new Author("Rod", "John");
		Book rodbook = new Book("J2EE Development", "456789");
		rodauthor.getBooks().add(rodbook);
		rodbook.getAuthors().add(rodauthor);
		rodbook.setPublisher(publisher);
		publisher.getBooks().add(rodbook);

		authorRepository.save(rodauthor);
		bookRepository.save(rodbook);
		publisherRepository.save(publisher);

		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher Number of Books " + publisher.getBooks().size());
	}

}
