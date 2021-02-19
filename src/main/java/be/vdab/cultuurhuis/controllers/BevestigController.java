package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.ReserveerLijnForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bevestig")
public class BevestigController {
    private final KlantRepository klantRepository;
    private final VoorstellingRepository voorstellingRepository;
    private final ReservatieRepository reservatieRepository;
    private final Mandje mandje;

    public BevestigController(KlantRepository klantRepository,
                              VoorstellingRepository voorstellingRepository,
                              ReservatieRepository reservatieRepository, Mandje mandje) {
        this.klantRepository = klantRepository;
        this.voorstellingRepository = voorstellingRepository;
        this.reservatieRepository = reservatieRepository;
        this.mandje = mandje;
    }

    @GetMapping
    public ModelAndView bevestig(){
        ModelAndView modelAndView = new ModelAndView("bevestig");
        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView bevestigKlant(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("bevestig");
        modelAndView.addObject("klant", klantRepository.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView vastleggenMandje(@PathVariable long id){
        List<Voorstelling> gelukteReservaties = new ArrayList<>();
        List<ReserveerLijnForm> mislukteReservaties = new ArrayList<>();

        for (Map.Entry<Long, Integer> i : mandje.getReserveerLijnen().entrySet()){
            voorstellingRepository.findById(i.getKey()).ifPresent(voorstelling -> {
                if (voorstelling.getVrijeplaatsen() >= i.getValue()){
                    voorstelling.setVrijeplaatsen(voorstelling.getVrijeplaatsen() - i.getValue());
                    gelukteReservaties.add(voorstelling);
                    voorstellingRepository.save(voorstelling);
                    reservatieRepository.save(new Reservatie(
                            klantRepository.findById(id).get(),
                            voorstelling,
                            i.getValue()
                    ));
                } else {
                    mislukteReservaties.add(new ReserveerLijnForm(voorstelling, i.getValue()));
                }
            });
        }
        mandje.leegMaken();
        ModelAndView modelAndView = new ModelAndView("overzicht");
        modelAndView.addObject("geluktetxn", gelukteReservaties);
        modelAndView.addObject("misluktetxn", mislukteReservaties);
        return modelAndView;
    }

}
