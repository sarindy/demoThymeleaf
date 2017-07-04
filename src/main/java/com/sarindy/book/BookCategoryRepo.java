package com.sarindy.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepo extends CrudRepository<BookCategory, Integer> {

	public BookCategory findByCategoryName(String name);

}
