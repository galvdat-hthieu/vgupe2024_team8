package Famacy.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.*;

@Embeddable
public class EmployeeID implements Serializable {

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Role\"")
    private String role;

    // Default constructor
    public EmployeeID() {}

    // Constructor with parameters
    public EmployeeID(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeID that = (EmployeeID) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }
}
