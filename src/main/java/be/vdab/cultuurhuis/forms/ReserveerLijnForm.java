package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReserveerLijnForm {
    private Voorstelling voorstelling;
    private int tePlaatsen;

    public ReserveerLijnForm(Voorstelling voorstelling, int tePlaatsen) {
        this.voorstelling = voorstelling;
        this.tePlaatsen = tePlaatsen;
    }

    public ReserveerLijnForm() {
    }

    public BigDecimal calculateLijnprijs(){
        return this.voorstelling.getPrijs().multiply(BigDecimal.valueOf(tePlaatsen));
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public void setVoorstelling(Voorstelling voorstelling) {
        this.voorstelling = voorstelling;
    }

    public int getTePlaatsen() {
        return tePlaatsen;
    }

    public void setTePlaatsen(int tePlaatsen) {
        this.tePlaatsen = tePlaatsen;
    }
}
