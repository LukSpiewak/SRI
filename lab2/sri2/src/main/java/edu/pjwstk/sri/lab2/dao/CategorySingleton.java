package edu.pjwstk.sri.lab2.dao;

import java.util.List;

import javax.ejb.Local;

import edu.pjwstk.sri.lab2.model.Category;

@Local
public interface CategorySingleton {
	
	List<Category> getAll();
	
	Category findById(Long id);

}
