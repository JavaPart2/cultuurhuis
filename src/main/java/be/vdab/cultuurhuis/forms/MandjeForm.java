package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MandjeForm {
    private List<ReserveerLijnForm> reserveerlijnen = new ArrayList<>();
    @NumberFormat(pattern = "0.00")
    private BigDecimal totaal;

    public MandjeForm() {
    }

    public void voegReserveerlijnToe(ReserveerLijnForm reserveerLijn){
        this.reserveerlijnen.add(reserveerLijn);
        this.setTotaal(this.totaal.add(reserveerLijn.calculateLijnprijs()));
    }

    public List<ReserveerLijnForm> getReserveerlijnen() {
        return reserveerlijnen;
    }

    public BigDecimal getTotaal() {
        return totaal;
    }

    public void setTotaal(BigDecimal totaal) {
        this.totaal = totaal;
    }
}
