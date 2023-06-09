package ibf2022.paf.day27workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.day27workshop.model.Review;
import ibf2022.paf.day27workshop.repository.ReviewRepository;
import jakarta.json.Json;

@RestController
@RequestMapping
public class ReviewRESTController {

    ReviewRepository repository;

    ReviewRESTController(ReviewRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/review", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addNewReview(@ModelAttribute Review review) {
        Review newReview = repository.addNewReview(review);
        if (newReview == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\": \"game " + review.getGid() + " not found\"}");
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newReview.toJson().toString());
    }

    
    @PutMapping(path = "/review/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateExistingReview(@PathVariable("id") String id, @RequestBody Review review) {
        Review existingReview = repository.getExistingReview(id);
        review.setCid(id);
        if (existingReview != null) {
            existingReview.setRating(review.getRating());
            existingReview.setComment(review.getComment());
            Long result = repository.updateExistingReview(existingReview);
            String response = "Documents updated: " + result;
    
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(Json.createObjectBuilder()
            .add("response", response)
            .build().toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(Json.createObjectBuilder()
        .add("response", "null")
        .build().toString());
    }
}
