package ibf2022.paf.day27workshop.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

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
        if (game != null) {
            review.setName(game.getName());
        } else {
            return null;
        }
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

    public long updateExistingReview(Review review) {
        Query query = Query.query(Criteria.where("_id").is(ObjectId.get()));
        Update updateOps = new Update().set("rating", review.getRating())
        .set("comments", review.getComment());
        UpdateResult result = template.updateFirst(query, updateOps, Document.class, "comments");
        return result.getModifiedCount();
    }



    public Review getExistingReview(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Review existingReview = template.findOne(query, Review.class, "reviews");
        
        return existingReview != null ? existingReview : null;
    }
}

// {
//   "_id": "643bc58229a76048793c0de9",
//   "user": "test",
//   "rating": 5,
//   "comment": "test2",
//   "id": 5,
//   "posted": {
//     "$date": "2023-04-16T09:53:06.778Z"
//   },
//   "name": "Acquire"
// }