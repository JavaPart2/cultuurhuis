package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.forms.AantalPlaatsenForm;
import be.vdab.cultuurhuis.forms.MandjeForm;
import be.vdab.cultuurhuis.forms.ReserveerLijnForm;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final VoorstellingRepository repository;

    public MandjeController(Mandje mandje, VoorstellingRepository repository) {
        this.mandje = mandje;
        this.repository = repository;
    }

    @GetMapping("{id}")
    public String toevoegenInMandje(@PathVariable int id, @Valid AantalPlaatsenForm form,
                                    Errors errors){
        if (errors.hasErrors()){
            return "redirect:/reserveren/" + id;
        }
        mandje.voegToe(id, form.getAantalPlaatsen());
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandje(){
        MandjeForm mandjeForm = new MandjeForm();
        ModelAndView modelAndView = new ModelAndView("mandje");

        if (!mandje.getReserveerLijnen().isEmpty()){
            for (Map.Entry<Long, Integer> i : mandje.getReserveerLijnen().entrySet()){
                repository.findById(i.getKey()).ifPresent(voorstelling -> {
                    mandjeForm.voegReserveerlijnToe(new ReserveerLijnForm(voorstelling,i.getValue()));
                });
            }
            modelAndView.addObject("mandjeform", mandjeForm);
        }
        return modelAndView;
    }

    @PostMapping
    public String verwijderUitMandje(@RequestParam List<Long> ids, Errors errors){
        for (long id: ids){
            mandje.verwijderReserveerlijn(id);
        }
        return "redirect:/mandje";
    }

}
