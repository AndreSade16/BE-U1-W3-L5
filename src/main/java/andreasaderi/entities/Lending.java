package andreasaderi.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lendings")
public class Lending {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;
    @Column(name = "lending_start_date", nullable = false)
    private LocalDate lendingStartDate;
    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;
    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    protected Lending() {
    }

    public Lending(User user, Publication publication, LocalDate lendingStartDate) {
        this.user = user;
        this.publication = publication;
        this.lendingStartDate = lendingStartDate;
        this.expectedReturnDate = lendingStartDate.plusDays(30);
        this.actualReturnDate = null;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public User getUser() {
        return user;
    }

    public Publication getPublication() {
        return publication;
    }

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", user=" + user +
                ", publication=" + publication +
                ", lendingStartDate=" + lendingStartDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }
}
