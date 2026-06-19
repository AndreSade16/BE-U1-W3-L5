package andreasaderi.dao;

import andreasaderi.entities.Publication;
import andreasaderi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
        System.out.println("La persona " + newPublication.getTitle() + " è stata salvata sul DB.");
    }

    public Publication findById(String id) {
        Publication result = entityManager.find(Publication.class, UUID.fromString(id));
        if (result == null) throw new NotFoundException(id);
        return result;
    }
}
