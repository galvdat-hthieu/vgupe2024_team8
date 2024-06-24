package Famacy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"TransactionItem\"")
public class TransactionItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "\"transaction_id\"")
    private Transaction transaction;

    @Column(name = "\"name\"")
    private String itemName;

    @Column(name = "\"quantity\"")
    private Integer quantity;

    @Column(name = "\"price\"")
    private Double price;
    
    @Column(name = "\"item_type\"")
    private String itemType;
    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getItemType(){
        return itemType;
    }
    
    public void setItemType(String type){
        itemType = type;
    }
    
}
