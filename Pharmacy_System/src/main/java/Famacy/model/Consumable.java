/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.model;

import javax.persistence.*;

@Entity
@Table(name = "\"Consumable\"")
public class Consumable implements Comparable<Consumable>{

    @EmbeddedId
    private ConsumableId id;

    @Column(name = "\"Supplied_date\"")
    private String suppliedDate;
    
    @Column(name = "\"Quantity\"")
    private Integer quantity;
    
    @Column(name = "\"CPrice\"")
    private double price;
    
    //Constructor
    public Consumable(ConsumableId id, String suppliedDate, 
            int quantity, double price){
        this.id = id;
        this.suppliedDate = suppliedDate;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Consumable() {
        this.id = null;
        this.suppliedDate = null;
        this.quantity = -1;
        this.price = -1;
    }

    public ConsumableId getId() {
        return id;
    }

    public void setId(ConsumableId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSuppliedDate() {
        return suppliedDate;
    }

    public void setSuppliedDate(String suppliedDate) {
        this.suppliedDate = suppliedDate;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    @Override
    public int compareTo(Consumable other) {
        // Compare by medicine name (id.getName())
            return this.id.getName().compareToIgnoreCase(other.getId().getName());
        }
    }
