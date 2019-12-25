package entity;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_client")
	private Long idClient;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="client")
	private List<Order> orders;

	public Client() {
	}

	public Client(Long id) {
		this.idClient = id;
	}

	public Long getIdClient() {
		return this.idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setClient(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setClient(null);

		return order;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id", "Name", "Phone"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{idClient, getName(), getPhone()};

	}

	@Override
	public Long getId() {
		return idClient;
	}

	@Override
	public void updateWith(Object mask) {
		Client obj = (Client) mask;
		name = obj.getName();
		phone = obj.getPhone();	

	}

}