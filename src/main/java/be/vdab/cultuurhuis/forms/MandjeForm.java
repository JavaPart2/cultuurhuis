package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MandjeForm {
    private Map<Voorstelling,Integer> reserveerLijnen = new LinkedHashMap<>();
    @NumberFormat(pattern = "0.00")
    private BigDecimal totaal;

    public MandjeForm() {
    }

    public Map<Voorstelling, Integer> getReserveerLijnen() {
        return reserveerLijnen;
    }

    public void setReserveerLijnen(Map<Voorstelling, Integer> reserveerLijnen) {
        this.reserveerLijnen = reserveerLijnen;
    }

    public BigDecimal getTotaal() {
        return totaal;
    }

    public void setTotaal(BigDecimal totaal) {
        this.totaal = totaal;
    }
}
