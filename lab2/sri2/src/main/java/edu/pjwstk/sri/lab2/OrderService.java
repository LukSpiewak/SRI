package edu.pjwstk.sri.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.dto.OrderItem;
import edu.pjwstk.sri.lab2.model.Product;

@Stateful
//@StatefulTimeout(unit = TimeUnit.MINUTES, value = 10)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderService {
	
	@Inject
	ProductDao productDao;
	
	@Resource
	private EJBContext context;
	
	private List<OrderItem> orders;
	
	@PostConstruct
	private void initializeBean() {
		orders = new ArrayList<OrderItem>();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void addProductToCart(OrderItem orderItem) {
		if (productDao.findById(orderItem.getProduct().getId()).getStock() >= orderItem.getAmount()) {
			System.out.println("orderAdded!");
			orders.add(orderItem);
		} else {
			System.out.println("Brak towaru w magazynie");
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void checkOut(String header) {
		System.out.println(header);
		for (OrderItem order: orders) {
			Product product = productDao.findById(order.getProduct().getId());
			if((product.getStock() - order.getAmount()) < 0) {
				System.out.println("Rollback!");
				context.setRollbackOnly();
				return;
			} else {
				product.setStock(product.getStock() - order.getAmount());
				productDao.update(product);
			}
		}
		orders.clear();
	}

	public void printOrders(String header) {
		System.out.println(header);
		for (OrderItem i : orders) {
			System.out.println(i.toString());
		}
		
	}

	public List<OrderItem> getOrders () {
		return orders;
	}
}