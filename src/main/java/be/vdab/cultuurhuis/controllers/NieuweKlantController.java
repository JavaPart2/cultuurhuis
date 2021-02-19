package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
        modelAndView.addObject(new NieuweKlantForm(new Klant(), ""));
        return modelAndView;
    }

    @PostMapping
    public String maakKlant(@Valid NieuweKlantForm form){
        if (form.getKlant().getPaswoord().equals(form.getHerhaalPas())){
            String geencrypteerd = new BCryptPasswordEncoder().encode(form.getHerhaalPas());
            form.getKlant().setPaswoord(geencrypteerd);
            repository.save(form.getKlant());
            long id = form.getKlant().getId();
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(form.getKlant().getGebruikersnaam(),
                        form.getHerhaalPas(), null));
            return "redirect:/bevestig/" + id;
        } else {
            // Herhaalpas is verschillend, foutboodschap tonen
            return "nieuweklant";
        }
    }
}
