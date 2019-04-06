package edu.pjwstk.sri.lab2.test;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.pjwstk.sri.lab2.OrderService;
import edu.pjwstk.sri.lab2.dao.CategorySingleton;
import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.dto.OrderItem;
import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;

@Named("testBean")
//@RequestScoped
public class TestBean implements Serializable {

	@Inject
	private CategorySingleton catService;
	
	@Inject
	private OrderService firstOrderService;

	@Inject
	private OrderService secondOrderService;

	@Inject
	private ProductDao productDao;
	
	public TestBean() {
	}
	
	public void test() {
		List<Category> listAll = catService.getAll();
		
		for(Category c : listAll) {
			System.out.println(c.toString());
		}
		
		List<Product> allProducts = productDao.listAll(null, null);
		for(Product p : allProducts) {
			System.out.println(p.toString());
		}
	
		System.out.println(productDao.findById(2000L));
		
		
	}
//	Product id: 2000 stock: 3
//	Product id: 2001 stock: 5
//	Product id: 2002 stock: 10
//	Product id: 2003 stock: 1
//	Product id: 2004 stock: 2
//	Product id: 2005 stock: 0
//	Product id: 2006 stock: 4
	
	public void cart1() {
		OrderItem item1 = findProduct(2000L, 2);
		firstOrderService.addProductToCart(item1);
//		firstOrderService.printOrders();
		
		OrderItem item2 = findProduct(2001L, 2);
		firstOrderService.addProductToCart(item2);
		firstOrderService.printOrders("Koszyk 1:");
		
		OrderItem item3 = findProduct(2000L, 2);
		secondOrderService.addProductToCart(item3);
		secondOrderService.printOrders("Koszyk 2:");
		
		firstOrderService.checkOut("Koszyk 1:");
		secondOrderService.checkOut("Koszyk 2:");
	}
	
	public void cart2() {
		OrderItem item1 = findProduct(2000L, 1);
		firstOrderService.addProductToCart(item1);
//		firstOrderService.printOrders();
		
		OrderItem item2 = findProduct(2000L, 1);
		firstOrderService.addProductToCart(item2);
		firstOrderService.printOrders("Koszyk 1:");
	}
	
	public void cart3() {
		OrderItem item1 = findProduct(2000L, 1);
		firstOrderService.addProductToCart(item1);
//		firstOrderService.printOrders();
		
		OrderItem item2 = findProduct(2001L, 2);
		firstOrderService.addProductToCart(item2);
		firstOrderService.printOrders("Koszyk 1:");
		
		OrderItem item3 = findProduct(2000L, 1);
		secondOrderService.addProductToCart(item3);
		secondOrderService.printOrders("Koszyk 2:");
		
		firstOrderService.checkOut("Koszyk 1:");
		secondOrderService.checkOut("Koszyk 2:");
	}
	
	
	private OrderItem findProduct(Long id, Integer amount) {
		Product product = productDao.findById(id);
		return new OrderItem(product, amount);
	}

}
