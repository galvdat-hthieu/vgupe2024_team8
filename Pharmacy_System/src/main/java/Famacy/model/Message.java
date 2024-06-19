package Famacy.model;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {

    @EmbeddedId
    private MessageId id;

    @Column(name = "\"content\"")
    private String content;

    // Getters and setters
    public MessageId getId() {
        return id;
    }

    public void setId(MessageId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
