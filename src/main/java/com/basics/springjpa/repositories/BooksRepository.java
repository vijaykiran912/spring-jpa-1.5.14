package com.basics.springjpa.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.basics.springjpa.entity.BooksEntity;

public interface BooksRepository extends CrudRepository<BooksEntity, Long>, JpaSpecificationExecutor<BooksEntity> {

	BooksEntity findByBookId(Long bookId);

	List<BooksEntity> findByTitleAndSeriesId(String title, Long seriesId);

	List<BooksEntity> findByAuthorAuthorIDBetween(Long from, Long to);

	List<BooksEntity> findByBookIdIn(Collection<Long> bookIds);

	List<BooksEntity> findAll(Specification<BooksEntity> spec);
}
