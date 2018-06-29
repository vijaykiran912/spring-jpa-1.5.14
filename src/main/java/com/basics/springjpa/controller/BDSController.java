package com.basics.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.basics.springjpa.entity.BooksEntity;
import com.basics.springjpa.repositories.BooksRepository;
import com.basics.springjpa.repositories.BooksRepositoryImpl;
import com.basics.springjpa.spec.CustomSpec;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/store")
public class BDSController {

	/*
	 * @Autowired private CustomServiceImpl serviceImpl;
	 */

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private BooksRepositoryImpl booksRepositoryImpl;

	@RequestMapping(value = "/getBook", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully Retrived the reconciliation items"),
			@ApiResponse(code = 400, message = "Missing required information in the request"),
			@ApiResponse(code = 500, message = "Internal error occured") })
	public BooksEntity getBook(
			@ApiParam(name = "bookId", value = "Book Id") @RequestParam(value = "bookId", required = false) Long bookId) {
		/* return serviceImpl.getBookByBookId(bookId); */

		return booksRepository.findOne(bookId);
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST, headers = "Accept=application/json")
	public BooksEntity saveBook(@RequestBody BooksEntity booksEntity) {
		return booksRepository.save(booksEntity);
	}

	@RequestMapping(value = "/getBooksByTitleAndSeriesId", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getBooksByTitleAndSeriesId(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "seriesId", required = true) Long seriesId) {
		return booksRepository.findByTitleAndSeriesId(title, seriesId);
	}

	@RequestMapping(value = "/findByAuthorIdBetween", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getBooksByAuthorIdBetween(@RequestParam(value = "from", required = true) Long from,
			@RequestParam(value = "to", required = true) Long to) {
		return booksRepository.findByAuthorAuthorIDBetween(from, to);
	}

	@RequestMapping(value = "/findByBookIdIn", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getByBookIdIn(@RequestParam List<Long> ids) {
		return booksRepository.findByBookIdIn(ids);
	}

	@RequestMapping(value = "/findByAutorIdSpec", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getByAutorIdSpec() {
		return booksRepository.findAll(CustomSpec.byAuthorId());
	}

	@RequestMapping(value = "/findBooksCAPI", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getBooksCAPI() {
		return booksRepositoryImpl.getBooks();
	}

	@RequestMapping(value = "/findBooksByAuthorIdJoin", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BooksEntity> getBooksCAPI(@RequestParam(value = "authorId", required = true) Long authorId) {
		return booksRepositoryImpl.getBooksByAuthorId(authorId);
	}

	@RequestMapping(value = "/restrictingCriteriaQueryResults", method = RequestMethod.GET, headers = "Accept=text/plain")
	public String restrictingCriteriaQueryResults() {
		booksRepositoryImpl.restrictingCriteriaQueryResults();
		return "success";
	}

	@RequestMapping(value = "/managingCriteriaQueryResults", method = RequestMethod.GET, headers = "Accept=text/plain")
	public String managingCriteriaQueryResults() {
		booksRepositoryImpl.managingCriteriaQueryResults();
		return "success";
	}
}
