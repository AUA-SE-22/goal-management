package com.datauser.goalmanagement.model.goals;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	@Serial
	@Transient
	private static final long serialVersionUID = 2681126372930615741L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "keycloakId", nullable = false)
	private String keycloakId;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(mappedBy = "employee")
	@JsonBackReference
	private Set<Goal> goals;

	public Employee() {
	}

	public Employee(String keycloakId, String firstName, String lastName, String email) {
		this.keycloakId = keycloakId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeycloakId() {
		return keycloakId;
	}

	public void setKeycloakId(String keycloakId) {
		this.keycloakId = keycloakId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return id.equals(employee.id) && keycloakId.equals(employee.keycloakId) &&
				firstName.equals(employee.firstName) && lastName.equals(employee.lastName) &&
				email.equals(employee.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, keycloakId, firstName, lastName, email);
	}
}
