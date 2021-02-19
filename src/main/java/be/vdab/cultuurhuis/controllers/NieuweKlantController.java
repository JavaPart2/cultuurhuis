package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/nieuweklant")
public class NieuweKlantController {
    private final KlantRepository repository;

    public NieuweKlantController(KlantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ModelAndView nieuw(){
        ModelAndView modelAndView = new ModelAndView("nieuweklant");
        modelAndView.addObject(new Klant());
        return modelAndView;
    }

    @PostMapping
    public String maakKlant(@Valid Klant klant, @Valid String herhaalPas){
        if (klant.getPaswoord().equals(herhaalPas)){
            String geencrypteerd = new BCryptPasswordEncoder().encode(herhaalPas);
            klant.setPaswoord(geencrypteerd);
            repository.save(klant);
            long id = klant.getId();
            return "redirect:/bevestig/" + id;
        } else {
            // Herhaalpas is verschillend, foutboodschap tonen
            return "nieuweklant";
        }
    }
}
