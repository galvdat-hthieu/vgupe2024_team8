package Famacy.model;

import javax.persistence.*;

@Entity
@Table(name = "\"Account\"")
public class Account implements java.io.Serializable {
    @Id
    @Column(name = "\"username\"")
    private String username;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"role\"")
    private String role;

    @Column(name = "\"employee_id\"")
    private int employeeId;

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
