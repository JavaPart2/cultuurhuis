package be.vdab.cultuurhuis.domain;

import javax.persistence.*;

@Entity
@Table(name = "klanten")
public class Klant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private String straat;
    private String huisnr;
    private String postcode;
    private String gemeente;
    private String gebruikersnaam;
    private String passwoord;

    public Klant(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente,
                 String gebruikersnaam, String passwoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gebruikersnaam = gebruikersnaam;
        this.passwoord = passwoord;
    }

    protected Klant() {
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPasswoord() {
        return passwoord;
    }
}
