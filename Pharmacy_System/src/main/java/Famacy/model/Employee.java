package Famacy.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "\"Employee\"")
public class Employee implements Serializable {
    @Id
    @Column(name = "\"EID\"")
    private int id;

    @Column(name = "\"EName\"")
    private String name;

    @Column(name = "\"Gender\"")
    private String gender;

    @Column(name = "\"Role\"")
    private String role;

    @Column(name = "\"Birth\"")
    private String birth;

    @Column(name = "\"Phone\"")
    private String phone;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
