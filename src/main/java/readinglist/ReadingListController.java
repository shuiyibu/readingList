/**
 * 
 */
package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by langdylan on 14/11/2017.
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {
	
    private  ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
		System.out.println("ReadingListController");

    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public  String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList=readingListRepository.findByReader(reader);

        if(readingList!=null){
            model.addAttribute("books",readingList);
        }

        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public  String addToReadingList(@PathVariable("reader")String reader,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return  "redirect:/readingList/{reader}";
    }
}