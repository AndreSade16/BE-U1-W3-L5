package andreasaderi.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue
    protected UUID id;
    @Column(nullable = false)
    protected long isbn;
    @Column(nullable = false)
    protected String title;
    @Column(name = "publication_year")
    protected int publicationYear;
    @Column(name = "pages_num")
    protected int pagesNum;

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
