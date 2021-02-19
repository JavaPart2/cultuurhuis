package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.domain.Klant;

public class NieuweKlantForm {
    private Klant klant;
    private String herhaalPas;

    public NieuweKlantForm(Klant klant, String herhaalPas) {
        this.klant = klant;
        this.herhaalPas = herhaalPas;
    }

    protected NieuweKlantForm() {
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public String getHerhaalPas() {
        return herhaalPas;
    }

    public void setHerhaalPas(String herhaalPas) {
        this.herhaalPas = herhaalPas;
    }
}
