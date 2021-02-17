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

}
