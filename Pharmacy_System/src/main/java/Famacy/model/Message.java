
package Famacy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"Message\"")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"MID\"", updatable = false, nullable = false)
    private int MID;

    @Column(name = "\"content\"")
    private String content;

    @Column(name = "\"receiverID\"")
    private int receiverID;

    @Column(name = "\"senderID\"")
    private int senderID;

    // Getters and setters
    public int getMID() {
        return MID;
    }

    public void setMID(int MID) {
        this.MID = MID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
