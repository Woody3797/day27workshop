package ibf2022.paf.day27workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.paf.day27workshop.exception.GameNotFoundException;
import ibf2022.paf.day27workshop.model.Game;
import ibf2022.paf.day27workshop.model.Review;
import ibf2022.paf.day27workshop.repository.BoardgameRepository;

@Service
public class BoardgameService {
    
    @Autowired
    private BoardgameRepository repository;

    public List<Game> getAllGames(Integer limit, Integer offset) {
        return repository.getAllGames(limit, offset);
    }

    public Game getBoardGameById(Integer gameId) {
        return repository.getBoardGameById(gameId);
    }

    public List<Game> getSortedBoardGames(Integer limit, Integer offset) {
        return repository.getSortedBoardGames(limit, offset);
    }

    public Review insertReview(Review review) throws GameNotFoundException {
        Game game = this.getBoardGameById(review.getGid());

        if (game == null) {
            System.out.println("error");
            throw new GameNotFoundException();
        }
        return null;

    }
}
