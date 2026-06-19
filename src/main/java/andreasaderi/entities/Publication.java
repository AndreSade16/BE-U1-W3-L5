package andreasaderi.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private long isbn;
    @Column(nullable = false)
    private String title;
    @Column(name = "publication_year")
    private int publicationYear;
    @Column(name = "pages_num")
    private int pagesNum;

    protected Publication() {
    }

    public Publication(long isbn, String title, int publicationYear, int pagesNum) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.pagesNum = pagesNum;
    }

    public String getTitle() {
        return title;
    }
}
