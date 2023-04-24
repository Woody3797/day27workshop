package ibf2022.paf.day27workshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EditedComment implements Serializable {
    
    private String comment;
    private Integer rating;
    private LocalDateTime posted;

    public EditedComment() {
    }

    public EditedComment(String comment, Integer rating, LocalDateTime posted) {
        this.comment = comment;
        this.rating = rating;
        this.posted = posted;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public LocalDateTime getPosted() {
        return posted;
    }
    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }


    
}
