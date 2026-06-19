package andreasaderi.dao;

import andreasaderi.entities.Lending;
import andreasaderi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LendingDAO {
    private final EntityManager entityManager;

    public LendingDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Lending newLending) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newLending);
        transaction.commit();
        System.out.println("Il prestito della pubblicazione " + newLending.getPublication().getTitle() + " concesso a " + newLending.getUser().getName() + " " + newLending.getUser().getSurname() + " è stato salvato sul DB.");
    }

    public Lending findById(String id) {
        Lending result = entityManager.find(Lending.class, UUID.fromString(id));
        if (result == null) throw new NotFoundException(id);
        return result;
    }

    public List<Lending> findExpiredLendings() {
        TypedQuery<Lending> query = entityManager.createQuery("SELECT l FROM Lending l WHERE l.expectedReturnDate < :now AND l.actualReturnDate IS NULL", Lending.class);
        query.setParameter("now", LocalDate.now());
        List<Lending> result = query.getResultList();
        if (result.isEmpty()) System.out.println("Nessun elemento prestato è ancora mancante oltre la data prevista.");
        else {
            System.out.println("Gli elementi ancora mancanti oltre la data prevista sono:");
            result.forEach(System.out::println);
        }

        return result;
    }
}
