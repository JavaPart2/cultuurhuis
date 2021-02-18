package be.vdab.cultuurhuis.sessions;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final int serialVersionUID = 1;
    private Boolean gevuld;
    private final Map<Long,Integer> reserveerLijnen = new LinkedHashMap<>();

    public Mandje() {
    }

    public Map<Long, Integer> getReserveerLijnen() {
        return reserveerLijnen;
    }

    public Boolean getGevuld() {
        return gevuld;
    }

    public Boolean bevatId(long id){
        return this.reserveerLijnen.containsKey(id);
    }

    public int geefValueVoorKey(long id){
        if (bevatId(id)){
            return this.reserveerLijnen.get(id);
        } else {
            return 0;
        }
    }

    public void voegToe(long id, int plaatsen){
        if (this.reserveerLijnen.putIfAbsent(id, plaatsen) != null){
            this.reserveerLijnen.replace(id, this.reserveerLijnen.get(id),
                    this.reserveerLijnen.get(id) + plaatsen);
        }
        this.gevuld = true;
    }

    public void verwijderReserveerlijn(long id){
        this.reserveerLijnen.remove(id);
    }

    public void leegMaken() {
        this.reserveerLijnen.clear();
        this.gevuld = false;
    }

}
