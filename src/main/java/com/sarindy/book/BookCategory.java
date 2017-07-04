package com.sarindy.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class BookCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
	private List<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public BookCategory(String categoryName, List<Book> books) {
		this.categoryName = categoryName;
		this.books = books;
	}

	public BookCategory() {

	}

}
