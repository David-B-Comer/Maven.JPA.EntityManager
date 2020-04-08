package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cd {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int year;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Artist> artist;
    private Double price;


    public Cd() {

    }

    public Cd(int id, String title, String description, int year, Set<Artist> artist, Double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = new HashSet<Artist>(artist);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
