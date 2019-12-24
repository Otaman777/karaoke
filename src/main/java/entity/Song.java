package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the songs database table.
 * 
 */
@Entity
@Table(name="songs")
@NamedQuery(name="Song.findAll", query="select s from Song s")
public class Song implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_song")
	private Long idSong;

	private String duration;

	@Column(name="name_song")
	private String nameSong;

	private Integer year;

	//bi-directional many-to-one association to Disk
	@ManyToOne
	@JoinColumn(name="id_disk")
	private Disk disk;

	public Song() {
	}

	public Long getIdSong() {
		return this.idSong;
	}

	public void setIdSong(Long idSong) {
		this.idSong = idSong;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNameSong() {
		return this.nameSong;
	}

	public void setNameSong(String nameSong) {
		this.nameSong = nameSong;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Disk getDisk() {
		return this.disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Song Name","Year","Duration", "Disk"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{idSong, getNameSong(), getYear(), getDuration(), getDisk()};
	}

	@Override
	public Long getId() {
		return idSong;
	}

	@Override
	public void updateWith(Object mask) {
		Song obj = (Song) mask;
		nameSong = obj.getNameSong();
		year = obj.getYear();
		duration = obj.getDuration();
		disk = getDisk();
	}

}