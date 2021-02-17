package be.vdab.cultuurhuis.domain;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    public Genre(String naam) {
        this.naam = naam;
    }

    protected Genre() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
