package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

public class Advertisement {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "advertisement")
    private Collection<Comment> comments;
    @OneToMany(mappedBy = "advertisement")
    private Collection<ProductPhoto> productPhotos;

    public Advertisement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(Collection<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id == that.id && price == that.price && Objects.equals(title, that.title) && Objects.equals(user, that.user) && Objects.equals(comments, that.comments) && Objects.equals(productPhotos, that.productPhotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, user, comments, productPhotos);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", user=" + user +
                ", comments=" + comments +
                ", productPhotos=" + productPhotos +
                '}';
    }
}
