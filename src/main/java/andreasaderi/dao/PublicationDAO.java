package andreasaderi.dao;

import andreasaderi.entities.Book;
import andreasaderi.entities.Publication;
import andreasaderi.entities.User;
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

    public List<Book> findByAuthor(String author) {

        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author) ", Book.class);
        query.setParameter("author", author);
        List<Book> result = query.getResultList();
        if (result.isEmpty())
            System.out.println("Nessun record con autore " + author + " trovato");
        else {
            System.out.println("I record con autore " + author + " sono:");
            result.forEach(System.out::println);
        }
        return result;
    }

    public List<Publication> findByTitlePortion(String portion) {
        TypedQuery<Publication> query = entityManager.createQuery("SELECT p FROM Publication p WHERE LOWER(p.title) LIKE LOWER(:portion)", Publication.class);
        query.setParameter("portion", "%" + portion + "%");
        List<Publication> result = query.getResultList();
        if (result.isEmpty())
            System.out.println("Nessun record con titolo contentente '" + portion + "' trovato");
        else {
            System.out.println("I record con titolo contentente '" + portion + "' sono:");
            result.forEach(System.out::println);
        }
        return result;
    }

    public User findUserByCardNumber(long cardNumber) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.cardNumber = :cardNumber", User.class);
        query.setParameter("cardNumber", cardNumber);
        User result = query.getSingleResult();
        if (result == null) {
            System.out.println("L'user con card number " + cardNumber + " non è stato trovato nel DB.");
        } else {
            System.out.println("L'user con card number " + cardNumber + " è " + result.getName() + " " + result.getSurname());
        }
        return result;
    }

    public List<Publication> findUnreturnedPublicationsByUserCardNumber(long cardNumber) {
        findUserByCardNumber(cardNumber);
        TypedQuery<Publication> query = entityManager.createQuery("SELECT p FROM Lending l JOIN l.publication p WHERE l.actualReturnDate IS NULL AND l.user.cardNumber = :cardNumber", Publication.class);
        query.setParameter("cardNumber", cardNumber);
        List<Publication> result = query.getResultList();
        if (result.isEmpty())
            System.out.println("Nessun record ancora in prestito per l'utente con card number " + cardNumber);
        else {
            System.out.println("I record  ancora in prestito per l'utente con card number" + cardNumber + " sono:");
            result.forEach(System.out::println);
        }
        return result;
    }

}



