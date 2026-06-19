package andreasaderi;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u1w3l5pu");

    public static void main(String[] args) {
        
        System.out.println("Hello World!");
    }
}
