package Famacy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Employee\"")
public class Employee {

    @Id
    @Column(name = "\"ID\"")
    private int EID;

    @Column(name = "\"Name\"")
    private String EName;

    @Column(name = "\"Gender\"")
    private String Gender;

    @Column(name = "\"Role\"")
    private String Role;

    @Column(name = "\"Birth\"")
    private String Birth;

    @Column(name = "\"Phone\"")
    private String Phone;

    // Constructor with all fields
    public Employee(int EID, String EName, String Gender, String Role, String Birth, String Phone) {
        this.EID = EID;
        this.EName = EName;
        this.Gender = Gender;
        this.Role = Role;
        this.Birth = Birth;
        this.Phone = Phone;
    }

    public int getId() {
        return EID;
    }

    public void setId(int EID) {
        this.EID = EID;
    }

    public String getName() {
        return EName;
    }

    public void setName(String EName) {
        this.EName = EName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String Birth) {
        this.Birth = Birth;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
}