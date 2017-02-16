package Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String displayBook(Model model){
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping (value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll() );
		return "addbook";
	}
	
	@RequestMapping (value = "/save", method=RequestMethod.POST)
	public String Savebook(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookid, Model model ){
		repository.delete(bookid);
		return "redirect:booklist";
	}
}
