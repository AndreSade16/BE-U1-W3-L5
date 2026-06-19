package andreasaderi.dao;

import andreasaderi.entities.Publication;
import andreasaderi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class PublicationDAO {
    private final EntityManager entityManager;

    public PublicationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Publication newPublication) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newPublication);
        transaction.commit();
        System.out.println("La pubblicazione " + newPublication.getTitle() + " è stata salvata sul DB.");
    }

    public Publication findById(String id) {
        Publication result = entityManager.find(Publication.class, UUID.fromString(id));
        if (result == null) throw new NotFoundException(id);
        return result;
    }

    public List<Publication> findAllPublications() {
        TypedQuery<Publication> query = entityManager.createQuery("SELECT p FROM Publication p", Publication.class);
        return query.getResultList();
    }


    public Publication findByISBN(long isbn) {
        TypedQuery<Publication> query = entityManager.createQuery("SELECT p FROM Publication p WHERE p.isbn = :isbn", Publication.class);
        query.setParameter("isbn", isbn);
        Publication result = query.getSingleResult();
        System.out.println("Il record con ISBN: " + isbn + " è: " + result.getTitle());
        return result;
    }

    public void removeByISBN(long isbn) {
        findByISBN(isbn);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE FROM Publication p WHERE p.isbn = :isbn");
        query.setParameter("isbn", isbn);
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il record con ISBN: " + isbn + " è stato rimosso dal DB.");
    }

    public List<Publication> findByPublicationYear(int publicationYear) {
        TypedQuery<Publication> query = entityManager.createQuery("SELECT p FROM Publication p WHERE p.publicationYear = :publicationYear", Publication.class);
        query.setParameter("publicationYear", publicationYear);
        List<Publication> result = query.getResultList();
        if (result.isEmpty())
            System.out.println("Nessun record con anno di pubblicazione " + publicationYear + " trovato");
        else {
            System.out.println("I record con anno di Pubblicazione: " + publicationYear + " sono:");
            result.forEach(System.out::println);
        }
        return result;
    }
}
