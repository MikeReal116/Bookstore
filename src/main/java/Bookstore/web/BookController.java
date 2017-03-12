package Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	// Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
	@RequestMapping(value="/booklist")
    public String displayBookStore(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
	
	// RESTful service to get all books   ,  it will show all students in JSON form
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();	
    }    
	
 // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);	
    } 
 // Add new student
   // @PreAuthorize("hasAuthority('ADMIN')")
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
