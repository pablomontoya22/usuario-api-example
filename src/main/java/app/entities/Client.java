package app.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Name can't be empty")
	private String name;
	@NotNull(message = "Last name can't be empty")
	private String surname;
	@NotNull(message = "DNI can't be empty")
	private String DNI;
	@Email(message = "Email should be valid")
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
    private Concessionaire concessionaire;

	public Client() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Concessionaire getConcessionaire() {
		return concessionaire;
	}

	public void setConcessionaire(Concessionaire concessionaire) {
		this.concessionaire = concessionaire;
	}

}
