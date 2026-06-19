package andreasaderi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Publication {
    @Column(nullable = false)
    private String author;
    private String genre;

    protected Book() {
    }

    public Book(long isbn, String title, int publicationYear, int pagesNum, String author, String genre) {
        super(isbn, title, publicationYear, pagesNum);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }
}
