package com.basics.springjpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basics.springjpa.entity.AuthorEntity;
import com.basics.springjpa.entity.BooksEntity;
import com.basics.springjpa.util.Utility;

/**
 * @author vipothamse
 *
 *         This class is used to work on Criteria API
 */
@Repository
public class BooksRepositoryImpl {

	@Autowired
	EntityManager dataSourceEntityManager;

	public List<BooksEntity> getBooks() {

		CriteriaBuilder cb = dataSourceEntityManager.getCriteriaBuilder();
		CriteriaQuery<BooksEntity> cq = cb.createQuery(BooksEntity.class); // Creating Query
		Root<BooksEntity> from = cq.from(BooksEntity.class); // Creating from
		cq.select(from); // Creating select clause

		gettingMetaModel(from);

		TypedQuery<BooksEntity> q = dataSourceEntityManager.createQuery(cq);
		List<BooksEntity> allitems = q.getResultList();
		return allitems;
	}

	/**
	 * Method used to work on different ways of getting meta model data
	 */
	private void gettingMetaModel(Root<BooksEntity> from) {

		// Way One
		EntityType<BooksEntity> BooksEntity_1 = from.getModel();
		System.out.println(BooksEntity_1);
		// End of one

		// Way two
		Metamodel m = dataSourceEntityManager.getMetamodel();
		EntityType<BooksEntity> BooksEntity_2 = m.entity(BooksEntity.class);
		System.out.println(BooksEntity_2);
	}

	/**
	 * @param authorId
	 * @return
	 * 
	 * 		JOIN applied
	 */
	public List<BooksEntity> getBooksByAuthorId(Long authorId) {

		CriteriaBuilder cb = dataSourceEntityManager.getCriteriaBuilder();
		CriteriaQuery<BooksEntity> cq = cb.createQuery(BooksEntity.class);
		Root<BooksEntity> books = cq.from(BooksEntity.class);
		Join<BooksEntity, AuthorEntity> author = books.join("author");

		cq.select(books).where(cb.equal(author.get("authorID"), authorId));

		TypedQuery<BooksEntity> q = dataSourceEntityManager.createQuery(cq);
		List<BooksEntity> allitems = q.getResultList();
		return allitems;
	}

	/*
	 * Start of Restricting Criteria Query Results
	 */
	/**
	 * Restricting Criteria Query Results
	 * <p>
	 * 1. The results of a query can be restricted on the CriteriaQuery object
	 * according to conditions set by calling the CriteriaQuery.where method
	 * <p>
	 * 2. The where method evaluates instances of the Expression interface.
	 * Expression instances are created by using methods defined in the Expression
	 * and CriteriaBuilder interfaces
	 */
	public void restrictingCriteriaQueryResults() {
		/* expressionInterfaceMethods(); */
		expressionMethodsInTheCriteriaBuilderInterface();
	}

	/**
	 * An Expression object is used in a query’s SELECT, WHERE, or HAVING clause
	 * <p>
	 * Methods : isNull , isNotNull, in
	 */
	@SuppressWarnings("unused")
	private void expressionInterfaceMethods() {
		CriteriaQuery<BooksEntity> cq = dataSourceEntityManager.getCriteriaBuilder().createQuery(BooksEntity.class);
		Root<BooksEntity> books = cq.from(BooksEntity.class);
		Join<BooksEntity, AuthorEntity> author = books.join("author");

		/*
		 * The following query uses the Expression.isNull method to find all books where
		 * the authorName attribute is null
		 */

		cq.where(author.get("authorName").isNotNull());
		Utility.printBooks(dataSourceEntityManager, cq, "all books where authorName attribute is null");

		/*
		 * Expression.in method to find all books where authorName in (Vijay, Ajay K
		 * Pandey)
		 */

		cq.where(author.get("authorName").in("Vijay", "Ajay K Pandey"));
		Utility.printBooks(dataSourceEntityManager, cq, "all books using 'in' expression");
	}

	/**
	 * The CriteriaBuilder interface defines additional methods correspond to the
	 * arithmetic, string, date, time, and case operators and functions of JPQL.
	 * <p>
	 * Methods: equal, notEqual, gt, ge, lt, le, between, like
	 */
	private void expressionMethodsInTheCriteriaBuilderInterface() {
		CriteriaBuilder cb = dataSourceEntityManager.getCriteriaBuilder();
		CriteriaQuery<BooksEntity> cq = cb.createQuery(BooksEntity.class);
		Root<BooksEntity> books = cq.from(BooksEntity.class);

		// The following code uses the CriteriaBuilder.equal method
		cq.where(cb.equal(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.equal method");

		// The following code uses the CriteriaBuilder.equal method
		cq.where(cb.notEqual(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.notEqual method");

		// The following code uses the CriteriaBuilder.gt method
		cq.where(cb.greaterThan(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.gt method");

		// The following code uses the CriteriaBuilder.ge method
		cq.where(cb.greaterThanOrEqualTo(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.ge method");

		// The following code uses the CriteriaBuilder.lt method
		cq.where(cb.lessThan(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.lt method");

		// The following code uses the CriteriaBuilder.le method
		cq.where(cb.lessThanOrEqualTo(books.get("seriesId"), 1));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.le method");

		// The following code uses the CriteriaBuilder.between method
		cq.where(cb.between(books.get("seriesId"), 1, 3));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.between method");

		// The following code uses the CriteriaBuilder.like method
		cq.where(cb.like(books.<String>get("title"), "%The%"));
		Utility.printBooks(dataSourceEntityManager, cq, "CriteriaBuilder.like method");

	}

	/*
	 * End of Restricting Criteria Query Results
	 */

}
