package _04_ShoppingCart.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class OrderBean implements Serializable{
	private Integer orderNo;
	private String invoiceTitle;
	private String bno;
	private String phone;
	private String email;
	private String city;
	private String district;
	private String address;
	private Timestamp orderDate;
	private Date deliverDate;
	private String CancelTag;
	private int totalAmount;
	private String comment;
	private Set<OrderItemBean> orderItemBean ;
	public OrderBean(Integer orderNo, String invoiceTitle, String bno, String phone, String email, String city,
			String district, String address, Timestamp orderDate, Date deliverDate, String cancelTag, int totalAmount,
			String comment) {
		super();
		this.orderNo = orderNo;
		this.invoiceTitle = invoiceTitle;
		this.bno = bno;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.district = district;
		this.address = address;
		this.orderDate = orderDate;
		this.deliverDate = deliverDate;
		CancelTag = cancelTag;
		this.totalAmount = totalAmount;
		this.comment = comment;
	}
	
	public OrderBean() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getCancelTag() {
		return CancelTag;
	}

	public void setCancelTag(String cancelTag) {
		CancelTag = cancelTag;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@OneToMany(mappedBy="orderBean",cascade=CascadeType.ALL)
	public Set<OrderItemBean> getOrderItemBean() {
		return orderItemBean;
	}

	public void setOrderItemBean(Set<OrderItemBean> orderItemBean) {
		this.orderItemBean = orderItemBean;
	}	
}
