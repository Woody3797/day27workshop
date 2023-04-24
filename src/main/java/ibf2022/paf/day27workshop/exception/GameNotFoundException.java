package ibf2022.paf.day27workshop.exception;

public class GameNotFoundException extends Exception {
    
    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String error) {
        super(error);
    }

}
