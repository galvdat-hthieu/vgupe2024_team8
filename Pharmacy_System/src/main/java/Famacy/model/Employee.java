package Famacy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Employee\"")
public class Employee {

    @Id
    @Column(name = "\"ID\"")
    private EmployeeID id;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Gender\"")
    private String gender;

    @Column(name = "\"Role\"")
    private String role;

    @Column(name = "\"Birth\"")
    private Date birth;

    @Column(name = "\"Phone\"")
    private String phone;

    // Constructor with all fields
    public Employee(EmployeeID id, String name, String gender, String role, Date birth, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.birth = birth;
        this.phone = phone;
    }

    public EmployeeID getId() {
        return id;
    }

    public void setId(EmployeeID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}