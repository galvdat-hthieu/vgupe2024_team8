/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MedicineId implements Serializable {

    @Column(name = "\"MName\"")
    private String name;

    @Column(name = "\"Batch\"")
    private String batchNumber;

    // Default constructor
    public MedicineId() {}

    // Constructor with parameters
    public MedicineId(String name, String batchNumber) {
        this.name = name;
        this.batchNumber = batchNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineId that = (MedicineId) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(batchNumber, that.batchNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, batchNumber);
    }
}