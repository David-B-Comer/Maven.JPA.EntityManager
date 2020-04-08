package services;

import entities.Artist;
import entities.Cd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class ArtistService {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("artist");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    public Artist create(int id, String first_name, String last_name, String instrument) {

        Artist artist = new Artist(id, first_name, last_name, instrument);
        artist.setId(id);
        artist.setFirst_name(first_name);
        artist.setLast_name(last_name);
        artist.setInstrument(instrument);

        entityTransaction.begin();
        entityManager.persist(artist);
        entityTransaction.commit();

        return artist;
    }

    public Artist create(Artist artist) {
        entityTransaction.begin();
        entityManager.persist(artist);
        entityTransaction.commit();

        return artist;
    }

    public Artist findById(int id) {
        return entityManager.find(Artist.class, id);
    }

    public List<Artist> findAll() {

        return entityManager.createQuery("SELECT z FROM Cd z").getResultList();
    }

    public void update(Artist artist) {
        List<Artist> artists = findAll();

        if (artists.contains(findById(artist.getId())))
            entityTransaction.begin();
        entityManager.merge(artist);
        entityTransaction.commit();
    }

    public void delete(int id) {
        Artist artist = findById(id);

        if (artist != null) {
            entityTransaction.begin();
            entityManager.remove(artist);
            entityTransaction.commit();
        }
    }
}
