package app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Concessionaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Last name can't be empty")
	private String address;
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private State state;
	@OneToMany(mappedBy = "concessionaire", cascade = CascadeType.ALL)
    private List<Client> comments = new ArrayList<Client>();

	public Concessionaire() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Client> getComments() {
		return comments;
	}

	public void setComments(List<Client> comments) {
		this.comments = comments;
	}

}
