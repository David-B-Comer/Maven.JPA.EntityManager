package services;

import entities.Artist;
import entities.Cd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;


public class CdService {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cd");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    public Cd create(int id, String title, String description, int year, Set<Artist> artist, Double price) {

        Cd cd = new Cd(id, title, description, year, artist, price);
        cd.setTitle(title);
        cd.setDescription(description);
        cd.setYear(year);
        cd.setArtist(artist);
        cd.setPrice(price);

        entityTransaction.begin();
        entityManager.persist(cd);
        entityTransaction.commit();

        return cd;
    }

    public Cd create(Cd cd) {
        entityTransaction.begin();
        entityManager.persist(cd);
        entityTransaction.commit();

        return cd;
    }

    public Cd findById(int id) {
        return entityManager.find(Cd.class, id);
    }

    public List<Cd> findAll() {

        return entityManager.createQuery("SELECT z FROM Cd z").getResultList();
    }

    public void update(Cd cd) {
        List<Cd> cds = findAll();

        if(cds.contains(findById(cd.getId())))
            entityTransaction.begin();
            entityManager.merge(cd);
            entityTransaction.commit();
    }

    public void delete(int id) {
        Cd cd = findById(id);

        if(cd != null) {
            entityTransaction.begin();
            entityManager.remove(cd);
            entityTransaction.commit();
        }
    }

}
