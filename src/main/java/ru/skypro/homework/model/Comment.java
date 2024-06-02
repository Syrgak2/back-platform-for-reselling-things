package ru.skypro.homework.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String message;
    private LocalDateTime messageDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(message, comment.message) && Objects.equals(messageDateTime, comment.messageDateTime) && Objects.equals(user, comment.user) && Objects.equals(advertisement, comment.advertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, messageDateTime, user, advertisement);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", messageDateTime=" + messageDateTime +
                ", user=" + user +
                ", advertisement=" + advertisement +
                '}';
    }
}
