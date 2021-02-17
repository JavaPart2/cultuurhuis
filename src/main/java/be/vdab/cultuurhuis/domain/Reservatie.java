package be.vdab.cultuurhuis.domain;

import javax.persistence.*;

@Entity
@Table(name = "reservaties")
public class Reservatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klantid")
    private Klant klant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voorstellingid")
    private Voorstelling voorstelling;
    private int plaatsen;

    public Reservatie(Klant klant, Voorstelling voorstelling, int plaatsen) {
        this.klant = klant;
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public Reservatie() {
    }

    public long getId() {
        return id;
    }

    public Klant getKlant() {
        return klant;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public int getPlaatsen() {
        return plaatsen;
    }
}
