package andreasaderi.entities;

import andreasaderi.entities.enums.Periodicity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends Publication {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    protected Magazine() {
    }

    public Magazine(long isbn, String title, int publicationYear, int pagesNum, Periodicity periodicity) {
        super(isbn, title, publicationYear, pagesNum);
        this.periodicity = periodicity;
    }
}
