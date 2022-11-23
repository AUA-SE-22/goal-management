package com.datauser.goalmanagement.model.keycloak;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USER_ATTRIBUTE")
public class UserAttribute implements Serializable {

    @Serial
    private static final long serialVersionUID = -4439056188068173594L;
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonManagedReference
    private User user;

    public UserAttribute() {
    }

    public UserAttribute(String id, String userId, String name, String value) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAttribute that = (UserAttribute) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, value);
    }
}
