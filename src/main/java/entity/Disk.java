package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the disks database table.
 * 
 */
@Entity
@Table(name="disks")
@NamedQuery(name="Disk.findAll", query="SELECT d FROM Disk d")
public class Disk implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_disk")
	private Long idDisk;

	private Date date;

	@Column(name="edition_count")
	private Integer editionCount;

	@Column(name="name_disk")
	private String nameDisk;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="id_group")
	private Group group;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="disk")
	private List<Song> songs;

	public Disk() {
	}

	public Long getIdDisk() {
		return this.idDisk;
	}

	public void setIdDisk(Long idDisk) {
		this.idDisk = idDisk;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getEditionCount() {
		return this.editionCount;
	}

	public void setEditionCount(Integer editionCount) {
		this.editionCount = editionCount;
	}

	public String getNameDisk() {
		return this.nameDisk;
	}

	public void setNameDisk(String nameDisk) {
		this.nameDisk = nameDisk;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setDisk(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setDisk(null);

		return song;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Name disk","Date","Edition Count","Group"};
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String s = myFormat.format(date);
		return new Object[]{idDisk,getNameDisk(), s, getEditionCount(),getGroup()};
	}

	@Override
	public Long getId() {
		return idDisk;
	}

	@Override
	public void updateWith(Object mask) {
		Disk obj = (Disk) mask;
		nameDisk = obj.getNameDisk();
		date = obj.getDate();
		editionCount = obj.getEditionCount();
		group = obj.getGroup();
	}

}