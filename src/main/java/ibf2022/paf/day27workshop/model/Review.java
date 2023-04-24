package ibf2022.paf.day27workshop.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review extends EditedComment {
    
    private String cid;
    private String user;
    private Integer gid;
    private String name;
    private List<EditedComment> ec = new LinkedList<>();
    
    public Review() {
    }

    public Review(String cid, String user, Integer rating, String comment, Integer gid, LocalDateTime posted,
            String name) {
        this.cid = cid;
        this.user = user;
        this.gid = gid;
        this.name = name;
    }

    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<EditedComment> getEc() {
        return ec;
    }
    public void setEc(List<EditedComment> ec) {
        this.ec = ec;
    }
    

    public static Review create(Document doc) {
        Review review = new Review();
        review.setGid(doc.getInteger("gid"));
        review.setName(doc.getString("name"));
        review.setPosted(LocalDateTime.now());
        review.setUser(doc.getString("user"));
        review.setRating(doc.getInteger("rating"));
        review.setComment(doc.getString("c_text"));
        review.setCid(doc.getString("c_id"));
        return review;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("user", getUser())
        .add("rating", getRating())
        .add("c_text", getComment())
        .add("gid", getGid())
        .add("posted", getPosted().toString())
        .add("name", getName())
        .add("cid", getCid())
        .build();
    }

}
