package com.basics.springjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basics.springjpa.entity.BooksEntity;
import com.basics.springjpa.repositories.BooksRepository;

@Service
public class CustomServiceImpl {

	@Autowired
	private BooksRepository booksRepository;

	public BooksEntity getBookByBookId(Long bookId) {
		BooksEntity book = booksRepository.findByBookId(bookId);
		/*BeanUtils.copyProperties(chargeTypesEntity, chargeTypes);*/
		return book;
	}

}
