package ibf2022.paf.day27workshop.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review {
    
    private String user;
    private Double rating;
    private String comment;
    private Integer id;
    private LocalDateTime posted;
    private String name;
    private List<String> edited;
    
    public Review() {
    }

    public Review(String user, Double rating, String comment, Integer id, LocalDateTime posted, String name,
            List<String> edited) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.id = id;
        this.posted = posted;
        this.name = name;
        this.edited = edited;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getPosted() {
        return posted;
    }
    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getEdited() {
        return edited;
    }
    public void setEdited(List<String> edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Review [user=" + user + ", rating=" + rating + ", comment=" + comment + ", id=" + id + ", posted="
                + posted + ", name=" + name + ", edited=" + edited + "]";
    }


    public static Review create(Document doc) {
        Review review = new Review();
        review.setId(doc.getInteger("id"));
        review.setName(doc.getString("name"));
        review.setPosted(LocalDateTime.now());

        return review;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("user", getUser())
        .add("rating", getRating())
        .add("comment", getComment())
        .add("id", getId())
        .add("posted", getPosted().toString())
        .add("name", getName())
        .build();
    }
    

}
