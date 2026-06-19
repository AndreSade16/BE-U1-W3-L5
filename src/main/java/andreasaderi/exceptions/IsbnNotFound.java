package andreasaderi.exceptions;

public class IsbnNotFound extends RuntimeException {
    public IsbnNotFound(long isbn) {
        super("Il record con ISBN " + isbn + " non è stato trovato nel DB.");
    }
}
