package _04_ShoppingCart.model.dao;

import java.util.List;

import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderItemBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);

	OrderBean getOrder(int orderNo);

	List<OrderBean> getAllOrders();

	List<OrderItemBean> getOssanOrders(int ossanNo);

}