package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.forms.AantalPlaatsenForm;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reserveren")
public class ReserveerController {
    private final VoorstellingRepository voorstellingRepository;
    private final Mandje mandje;

    public ReserveerController(VoorstellingRepository voorstellingRepository, Mandje mandje) {
        this.voorstellingRepository = voorstellingRepository;
        this.mandje = mandje;
    }

    @GetMapping("/{id}")
    public ModelAndView toonVoorstelling(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("reserveer");
        modelAndView.addObject("voorstelling", voorstellingRepository.findById(id).get());
        modelAndView.addObject("aantalplaatsen", new AantalPlaatsenForm(mandje.geefValueVoorKey(id)));
        modelAndView.addObject(new Reservatie());
        return modelAndView;
    }

}
