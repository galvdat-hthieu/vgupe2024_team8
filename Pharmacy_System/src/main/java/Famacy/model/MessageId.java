package Famacy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MessageId implements Serializable {

    @Column(name = "\"senderID\"")
    private Integer senderID;

    @Column(name = "\"receiverID\"")
    private Integer receiverID;

    // Default constructor
    public MessageId() {
    }

    // Parameterized constructor
    public MessageId(Integer senderID, Integer receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    // Getters and setters
    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(senderID, receiverID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MessageId that = (MessageId) obj;
        return Objects.equals(senderID, that.senderID) &&
                Objects.equals(receiverID, that.receiverID);
    }
}
