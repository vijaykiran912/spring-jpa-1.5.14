package com.basics.springjpa.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.basics.springjpa.entity.BooksEntity;

public class CustomSpec {
	public static Specification<BooksEntity> byAuthorId() {
		return new Specification<BooksEntity>() {

			@Override
			public Predicate toPredicate(Root<BooksEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				return builder.equal(root.get("authorId"), 4);
			}

		};
	}
}
