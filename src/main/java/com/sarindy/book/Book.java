package com.sarindy.book;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_category_id")
	private BookCategory bookCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Book() {

	}

	public Book(String name, Date createdDate, BookCategory bookCategory) {

		this.name = name;
		this.createdDate = createdDate;
		this.bookCategory = bookCategory;
	}

}
