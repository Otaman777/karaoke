package entity;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the group database table.
 * 
 */
@Entity
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_group")
	private Long idGroup;

	private String name;

	private String style;

	//bi-directional many-to-one association to Disk
	@OneToMany(mappedBy="group")
	private List<Disk> disks;

	public Group() {
	}

	public Long getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public List<Disk> getDisks() {
		return this.disks;
	}

	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}

	public Disk addDisk(Disk disk) {
		getDisks().add(disk);
		disk.setGroup(this);

		return disk;
	}

	public Disk removeDisk(Disk disk) {
		getDisks().remove(disk);
		disk.setGroup(null);

		return disk;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Style","Name"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{idGroup, getStyle(),getName()};
	}

	@Override
	public Long getId() {
		return idGroup;
	}

	@Override
	public void updateWith(Object mask) {
		Group obj = (Group) mask;
		name = obj.getName();
		style = obj.getStyle();
	}
	@Override
	public String toString (){
		String s = getName();
		return s;
	}

}