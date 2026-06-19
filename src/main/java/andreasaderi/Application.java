package andreasaderi;

import andreasaderi.dao.LendingDAO;
import andreasaderi.dao.PublicationDAO;
import andreasaderi.dao.UserDAO;
import andreasaderi.entities.*;
import andreasaderi.entities.enums.Periodicity;
import andreasaderi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {

    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u1w3l5pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        PublicationDAO pd = new PublicationDAO(em);
        UserDAO ud = new UserDAO(em);
        LendingDAO ld = new LendingDAO(em);

//        Creo manualmente delle istanze per testare le tabelle
//        Users:
        User user1 = new User("Andrea", "Saderi", LocalDate.of(1996, 5, 28), 12513);
        User user2 = new User("Alessia", "Cotini", LocalDate.of(1998, 2, 16), 13611);
        User user3 = new User("Roberto", "Cafagna", LocalDate.of(1999, 12, 25), 22510);

//        Publications:

        Publication publication1 = new Book(13411, "21 Consigli per il 21esimo secolo", 2021, 500, "Noah Yuval Harari", "Science");
        Publication publication2 = new Book(13412, "Behave", 2019, 550, "Robert M. Sapolsky", "Science");
        Publication publication3 = new Book(13413, "Code da Vinci", 2011, 350, "Dan Brown", "Fiction");
        Publication publication4 = new Magazine(13415, "Io Donna", 2026, 30, Periodicity.MONTHLY);
        Publication publication5 = new Magazine(13416, "Topolino", 2026, 100, Periodicity.WEEKLY);
        Publication publication6 = new Magazine(13417, "Paperino", 2026, 150, Periodicity.MONTHLY);

//        Salvo le varie istanze sul db in modo da renderle managed e avere degli ID da inserire nei lendings:

//        ud.save(user1);
//        ud.save(user2);
//        ud.save(user3);
//
//        pd.save(publication1);
//        pd.save(publication2);
//        pd.save(publication3);
//        pd.save(publication4);
//        pd.save(publication5);
//        pd.save(publication6);

//        Cerco e salvo in variabili le varie istanze sul Db per poi passarle nei lendings:

        User user1FromDb = null;
        User user2FromDb = null;
        User user3FromDb = null;

        try {
            user1FromDb = ud.findById("cbfea3f4-8376-47cb-9f7c-e9e678e90371");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            user2FromDb = ud.findById("ae8b9f61-62b4-4208-ba1c-44541fc8c842");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            user3FromDb = ud.findById("a52d3ea2-e610-48d2-9075-eb3832446cdb");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        Publication publication1FromDb = null;
        Publication publication2FromDb = null;
        Publication publication3FromDb = null;
        Publication publication4FromDb = null;
        Publication publication5FromDb = null;
        Publication publication6FromDb = null;


        try {
            publication1FromDb = pd.findById("b8c99ae2-6195-462b-a1c1-ca26d1121193");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            publication2FromDb = pd.findById("18519e28-d725-49bd-8a80-909f1213e868");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            publication3FromDb = pd.findById("62478492-f3d1-49c6-b63c-b88c3ada3999");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            publication4FromDb = pd.findById("8c114c74-3d00-4150-835f-637eb7dd2647");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            publication5FromDb = pd.findById("6c8513c4-3cfb-48bc-90af-98e67bb5783a");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            publication6FromDb = pd.findById("135f7bfb-57a6-4002-a8d2-764c54be5392");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

//        Lendings:

        Lending lending1 = new Lending(user1FromDb, publication1FromDb, LocalDate.now());
        Lending lending2 = new Lending(user2FromDb, publication3FromDb, LocalDate.of(2026, 4, 19));
        Lending lending3 = new Lending(user3FromDb, publication2FromDb, LocalDate.now());
        Lending lending4 = new Lending(user3FromDb, publication5FromDb, LocalDate.now());
        Lending lending5 = new Lending(user1FromDb, publication6FromDb, LocalDate.now());


//        ld.save(lending1);
//        ld.save(lending2);
//        ld.save(lending3);
//        ld.save(lending4);
//        ld.save(lending5);

/*
        Consegna:

        Aggiunta di un elemento del catalogo: pd.save() OK

        Rimozione di un elemento del catalogo dato un codice ISBN: pd.removeByISBN() OK
 */
//        test: try {
//            pd.removeByISBN(1341551);
//        } catch (NoResultException ex) {
//            System.out.println(ex.getMessage());
//        }

//      Ricerca per ISBN: pd.findByISBN() OK

//        test: try {
//            pd.findByISBN(13411314);
//        } catch (NoResultException ex) {
//            System.out.println(ex.getMessage());
//        }

//        Ricerca per anno di pubblicazione: pd.findByPublicationYear() OK

//        test: pd.findByPublicationYear(2027);

//        Ricerca per autore: pd.findByAuthor() OK

//        test: pd.findByAuthor("Daniel Brown");

//        Ricerca per titolo o parte di esso: pd.findByTitlePortion() OK

//          test: pd.findByTitlePortion("aadafafadf");

//        Ricerca degli elementi attualmente in prestito dato un numero di tessera utente: findUnreturnedPublicationsByUserCardNumber()           OK

//        test: pd.findUnreturnedPublicationsByUserCardNumber(13611);

//        Ricerca di tutti i prestiti scaduti e non ancora restituiti: findExpiredLendings() OK?

//        test: ld.findExpiredLendings();

    }
}
