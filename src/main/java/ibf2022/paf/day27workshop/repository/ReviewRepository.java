package ibf2022.paf.day27workshop.repository;

import java.time.LocalDateTime;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day27workshop.model.Game;
import ibf2022.paf.day27workshop.model.Review;

@Repository
public class ReviewRepository {
    
    @Autowired
    MongoTemplate template;

    public Review addNewReview(Review review) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gid").is(review.getId()));
        Game game = template.findOne(query, Game.class, "games");
        review.setPosted(LocalDateTime.now());
        review.setName(game.getName());
        Document toInsert = new Document();
        toInsert.put("user", review.getUser());
        toInsert.put("rating", review.getRating());
        toInsert.put("comment", review.getComment());
        toInsert.put("id", review.getId());
        toInsert.put("posted", review.getPosted());
        toInsert.put("name", review.getName());
        Document newDoc = template.insert(toInsert, "reviews");
        ObjectId id = newDoc.getObjectId("_id");
        System.out.println(id.toString());
        return review;
    }
}
