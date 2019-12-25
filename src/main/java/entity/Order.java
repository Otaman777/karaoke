package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_order")
	private Long idOrder;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer duration;

	@Column(name="price_all")
	private Integer priceAll;

	private Integer time;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	public Order() {
	}

	public Order(Date date, Integer duration, Integer priceAll, Integer time, Client client) {
		this.date = date;
		this.duration = duration;
		this.priceAll = priceAll;
		this.time = time;
		this.client = client;
	}

	public Long getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getPriceAll() {
		return this.priceAll;
	}

	public void setPriceAll(Integer priceAll) {
		this.priceAll = priceAll;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public String[] getTableHeaders() {
		return new String[]{"Date", "Time", "Duration"};
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String s = myFormat.format(date);
		return new Object[]{s, getTime(), getDuration()};
	}

	@Override
	public Long getId() {
		return idOrder;
	}

	@Override
	public void updateWith(Object mask) {
		Order obj = (Order) mask;
		date = obj.getDate();
		time = obj.getTime();
		priceAll = obj.getPriceAll();
		duration = obj.getDuration();
		client = obj.getClient();
	}

}