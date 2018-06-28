package com.basics.springjpa.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.basics.springjpa.entity.BooksEntity;

public class Utility {
	public static void printBooks(EntityManager dataSourceEntityManager, CriteriaQuery<BooksEntity> cq,
			String message) {
		TypedQuery<BooksEntity> q = dataSourceEntityManager.createQuery(cq);
		List<BooksEntity> allitems = q.getResultList();

		System.out.println("*****************************************************************");
		System.out.println("Start printing " + message + "\n");

		for (BooksEntity books : allitems) {
			System.out.println(books);
		}

		System.out.println("\n" + "End printing " + message);
		System.out.println("*****************************************************************");
	}
}
