package _04_ShoppingCart.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import _03_listOssans.model.OssanBean;
@Entity
public class OrderItemBean implements Serializable{
	private Integer seqNo;
	private int quantity;
	private int unitPrice;
	private double discount;
	private OssanBean ossanBean = new OssanBean();
	private OrderBean orderBean = new OrderBean();
	public OrderItemBean(Integer seqNo, int quantity, int unitPrice, double discount) {
		super();
		this.seqNo = seqNo;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}
	public OrderItemBean() {}
	
	@Id
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@ManyToOne
	@JoinColumn(name="FK_ossanNo")
	public OssanBean getOssanBean() {
		return ossanBean;
	}
	public void setOssanBean(OssanBean ossanBean) {
		this.ossanBean = ossanBean;
	}
	@ManyToOne
	@JoinColumn(name="FK_orderNo")
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
	
}
