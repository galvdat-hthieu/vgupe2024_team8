/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"Consumable\"")
public class Consumable implements Serializable {

    @EmbeddedId
    private ConsumableId id;

    @Column(name = "\"Quantity\"")
    private Integer quantity;

    @Column(name = "\"Supplied_date\"")
    private String suppliedDate;

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
}
