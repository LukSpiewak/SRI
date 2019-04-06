package edu.pjwstk.sri.lab2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.pjwstk.sri.lab2.model.Category;

@Startup
@Singleton
public class CategorySingletonImpl implements CategorySingleton {
	
	private List<Category> allCategories = new ArrayList<Category>();

	@PersistenceContext(unitName = "sri2-persistence-unit")
	private EntityManager em;
 
	public CategorySingletonImpl() {
	}
	
	@PostConstruct
	public void initialize() {
		refreshCategory();
	}

	@Override
	public List<Category> getAll() {
		return allCategories;
	}

	@Override
	public Category findById(Long id) {
		Category result = null;
		for(Category c : allCategories) {
			if (c.getId().equals(id)) {
				result = c;
			}
		}
		return result;
	}
	
	@Schedule(second = "*/10", minute = "*", hour = "*")
	private void refreshCategory() {
		allCategories.clear();
		allCategories.addAll(listAll());
	}
	
	private List<Category> listAll() {
		TypedQuery<Category> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.parentCategory LEFT JOIN FETCH c.childCategories ORDER BY c.id",
						Category.class);
	
		return findAllQuery.getResultList();
	}
	
}
