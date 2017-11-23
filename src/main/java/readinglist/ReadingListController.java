/**
 * 
 */
package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by langdylan on 14/11/2017.
 */
@Controller
@RequestMapping("/")
@ConfigurationProperties("amazon")
public class ReadingListController {

	private String associatedId;
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;

	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties) {
		this.readingListRepository = readingListRepository;
		this.amazonProperties = amazonProperties;

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/fail")
	public void fail() {
	  throw new RuntimeException();
	}
	
	
	@ExceptionHandler(value=RuntimeException.class)
	@ResponseStatus(value=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
	public String error() {
	  return "error";
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);

		if (readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociatedId());
		}

		return "readingList";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}

	public void setAssociatedId(String associatedId) {
		this.associatedId = associatedId;
	}

	public void setReadingListRepository(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	public void setAmazonProperties(AmazonProperties amazonProperties) {
		this.amazonProperties = amazonProperties;
	}
	
	

}
