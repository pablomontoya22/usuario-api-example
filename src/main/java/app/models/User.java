package app.models;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NamedQueries(value = {
    @NamedQuery(name = User.FIND_BY_EMAIL, query = "select t from User t where t.email =:" + User.PARAM_EMAIL)
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	public static final String FIND_BY_EMAIL = "User.findByEmail";

	public static final String PARAM_EMAIL = "email";

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	@CreationTimestamp
	private Date created;

	@CreationTimestamp
	private Date modified;

	@CreationTimestamp
	@Column(name = "last_login")
	private Date lastLogin;

	@Column(name = "is_active")
	@Type(type= "org.hibernate.type.NumericBooleanType")
	private boolean isActive;

	@Column(length = 500)
	private String token;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phones;
}