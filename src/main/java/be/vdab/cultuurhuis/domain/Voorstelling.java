package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    private String uitvoerders;
    private LocalDateTime datum;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genreid")
    private Genre genre;
    private BigDecimal prijs;
    private int vrijeplaatsen;
    private int versie;

    public Voorstelling(String titel, String uitvoerders, LocalDateTime datum, Genre genre, BigDecimal prijs, int vrijeplaatsen) {
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = datum;
        this.genre = genre;
        this.prijs = prijs;
        this.vrijeplaatsen = vrijeplaatsen;
    }

    protected Voorstelling() {
    }

    public void setVrijeplaatsen(int vrijeplaatsen) {
        this.vrijeplaatsen = vrijeplaatsen;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public Genre getGenre() {
        return genre;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public int getVersie() {
        return versie;
    }
}
