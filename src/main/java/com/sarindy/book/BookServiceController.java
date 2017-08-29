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
	private BookCategory bookCategory;

	@RequestMapping("/")
	public ModelAndView index() {

		List<Book> books = new ArrayList<>();
		bookRepo.findAll().forEach(books::add);

		List<BookCategory> bookCategories = new ArrayList<>();
		bookCategoryRepo.findAll().forEach(bookCategories::add);

		/*
		 * We need to add this object to let the html pafe iterate through books
		 * list object
		 */
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", books);
		mv.addObject("bookCategories", bookCategories);

		mv.addObject("bookAdded", new Book());
		mv.addObject("bookCategoryAdded", new BookCategory());

		mv.setViewName("index");

		return mv;

	}

	@RequestMapping(value = "/addNewBook", method = RequestMethod.POST)
	public ModelAndView addNewBook(@ModelAttribute Book bookAdded, BindingResult errors, Model model) {
		bookCategory = bookCategoryRepo.findByCategoryName(bookAdded.getBookCategory().getCategoryName());
		bookAdded.setBookCategory(bookCategory);
		bookAdded.setCreatedDate(new Date());
		System.out.println(bookAdded.getName());
		System.out.println(bookAdded.getBookCategory().getCategoryName());
		bookRepo.save(bookAdded);

		ModelAndView mv = new ModelAndView();

		List<Book> books = new ArrayList<>();
		bookRepo.findAll().forEach(books::add);
		mv.addObject("books", books);

		List<BookCategory> bookCategories = new ArrayList<>();
		bookCategoryRepo.findAll().forEach(bookCategories::add);
		mv.addObject("bookCategories", bookCategories);

		// We need to add this object because in HTML TH:OBJECT of the index
		// have this bean
		mv.addObject("bookAdded", new Book());
		mv.addObject("bookCategoryAdded", new BookCategory());

		mv.setViewName("index");

		return mv;
	}

	@RequestMapping(value = "/addNewBookCategory", method = RequestMethod.POST)
	public ModelAndView addNewBookCategory(@ModelAttribute BookCategory bookCategoryAdded, BindingResult errors,
			Model model) {

		bookCategoryRepo.save(bookCategoryAdded);

		ModelAndView mv = new ModelAndView();
		List<Book> books = new ArrayList<>();
		bookRepo.findAll().forEach(books::add);
		mv.addObject("books", books);

		List<BookCategory> bookCategories = new ArrayList<>();
		bookCategoryRepo.findAll().forEach(bookCategories::add);
		mv.addObject("bookCategories", bookCategories);

		// We need to add this object because in HTML TH:OBJECT of the index
		// have this bean
		mv.addObject("bookAdded", new Book());
		mv.addObject("bookCategoryAdded", new BookCategory());

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

	@RequestMapping(value = "/bootstrap", method = RequestMethod.GET)
	public ModelAndView bootStrap() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("bootstrap");
		return mv;
	}
	
	@RequestMapping(value = "/bootstrap2", method = RequestMethod.GET)
	public ModelAndView bootStrap2() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("bootstrap2");
		return mv;
	}

}
