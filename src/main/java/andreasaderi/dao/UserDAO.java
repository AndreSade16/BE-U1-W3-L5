package andreasaderi.dao;

import andreasaderi.entities.Publication;
import andreasaderi.entities.User;
import andreasaderi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User newUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUser);
        transaction.commit();
        System.out.println("L'utente " + newUser.getName() + " " + newUser.getSurname() + " è stato salvato sul DB.");
    }

    public User findById(String id) {
        User result = entityManager.find(User.class, UUID.fromString(id));
        if (result == null) throw new NotFoundException(id);
        return result;
    }
}
