package edu.pjwstk.sri.lab2.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import edu.pjwstk.sri.lab2.model.Category;

/**
 * DAO for Category
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoryDao {
	@PersistenceContext(unitName = "sri2-persistence-unit")
	private EntityManager em;

	@Inject
	private CategorySingleton categoryCache;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Category entity) {
		em.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(Long id) {
		Category entity = em.find(Category.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Category findById(Long id) {
		return categoryCache.findById(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category update(Category entity) {
		return em.merge(entity);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Category> listAll() {
		return categoryCache.getAll();
	}
}
