package com.sarindy.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Service
@RestController
public class BookServiceController {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private BookCategoryRepo bookCategoryRepo;

	@Autowired
	private Book book;

	@Autowired
	private BookCategory bookCategory;

	@RequestMapping("/")
	public ModelAndView index() {

		List<BookCategory> bookCategories = new ArrayList<>();
		// BookCategory bookCategory= new BookCategory();
		bookCategory.setCategoryName("Programming");
		bookCategories.add(bookCategory);

		bookCategory = new BookCategory();
		bookCategory.setCategoryName("Design");
		bookCategories.add(bookCategory);

		bookCategory = new BookCategory();
		bookCategory.setCategoryName("Math");
		bookCategories.add(bookCategory);

		bookCategory = new BookCategory();
		bookCategory.setCategoryName("Algorithm");
		bookCategories.add(bookCategory);

		bookCategoryRepo.save(bookCategories);

		List<Book> books = new ArrayList<>();
		bookCategory = new BookCategory();
		bookCategory = bookCategoryRepo.findByCategoryName("Programming");
		book.setBookCategory(bookCategory);
		book.setName("Spring Security");
		book.setCreatedDate(new Date());
		books.add(book);

		book = new Book();
		bookCategory = new BookCategory();
		bookCategory = bookCategoryRepo.findByCategoryName("Design");
		book.setBookCategory(bookCategory);
		book.setName("Photoshop");
		book.setCreatedDate(new Date());
		books.add(book);

		book = new Book();
		bookCategory = new BookCategory();
		bookCategory = bookCategoryRepo.findByCategoryName("Math");
		book.setBookCategory(bookCategory);
		book.setName("Algebra");
		book.setCreatedDate(new Date());
		books.add(book);

		bookRepo.save(books);

		books = new ArrayList<>();
		bookRepo.findAll().forEach(books::add);

		/* 
		 * We need to add this object to let the html pafe iterate through books list object
		 * */
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", books);
		mv.setViewName("index");

		return mv;

	}

	@RequestMapping(value = "/addNewBook", method = RequestMethod.GET)
	public ModelAndView addNewBook(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("book", new Book());
		mv.setViewName("addBook");
		return mv;
	}

	@RequestMapping(value = "/addNewBook", method = RequestMethod.POST)
	public ModelAndView addNewBook(@ModelAttribute Book book, BindingResult errors, Model model) {
		bookCategory = bookCategoryRepo.findByCategoryName(book.getBookCategory().getCategoryName());
		book.setBookCategory(bookCategory);
		book.setCreatedDate(new Date());
		System.out.println(book.getName());
		System.out.println(book.getBookCategory().getCategoryName());
		bookRepo.save(book);
		ModelAndView mv = new ModelAndView();
		List<Book> books = new ArrayList<>();
		bookRepo.findAll().forEach(books::add);
		mv.addObject("books", books);
		mv.setViewName("index");

		return mv;
	}
	
	
	
	
	@RequestMapping(value = "/returnList", method = RequestMethod.GET)
	public ModelAndView returnList(Model model) {
		ModelAndView mv = new ModelAndView();
		List<String> javaList = new ArrayList<>();
		javaList.add("list Item1");
		javaList.add("list Item2");
		javaList.add("list Item3");
		javaList.add("list Item4");
		model.addAttribute("javaList", javaList);
		mv.setViewName("returnList");
		return mv;
	}

}
