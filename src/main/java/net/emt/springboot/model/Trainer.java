package net.emt.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trainers")
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name = "trainer_name")
	private String trainerName;
	
	@Column(name = "trainer_surname")
	private String trainerSurname;

	
	public Trainer() {
		super();
	}

	public Trainer(long id, String trainerName, String trainerSurname) {
		super();
		this.trainerName = trainerName;
		this.trainerSurname = trainerSurname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerSurname() {
		return trainerSurname;
	}

	public void setTrainerSurname(String trainerSurname) {
		this.trainerSurname = trainerSurname;
	}
	
	
}
