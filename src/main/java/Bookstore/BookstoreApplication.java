package Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.CategoryRepository;
import Bookstore.domain.Category;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		
		@Bean
		public CommandLineRunner booksDemo(BookRepository repository, CategoryRepository crepository) {
			return (args) -> {
				log.info("save a couple of students");
				crepository.save(new Category("Fiction"));
				crepository.save(new Category("Comic"));
				crepository.save(new Category("Educational"));
				crepository.save(new Category("Life"));
				repository.save(new Book("The call that changed", "Mike REAL", 2017, "A19008", 20,crepository.findByName("Life").get(0)));
				repository.save(new Book("Grief child", "Kofi Game", 2000, "A194874", 10, crepository.findByName("Fiction").get(0)));
				repository.save(new Book("In the chest of a woman", "MDK", 2010, "B271225", 20, crepository.findByName("Fiction").get(0)));
				log.info("fetch all books");
				for (Book book : repository.findAll()) {
					log.info(book.toString());
				}

			};
		}
}
