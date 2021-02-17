package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.repositories.GenreRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reserveren")
public class ReserveerController {
    private final VoorstellingRepository voorstellingRepository;

    public ReserveerController(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }

    @GetMapping("/{id}")
    public ModelAndView toonVoorstelling(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("reserveer");
        modelAndView.addObject("voorstelling", voorstellingRepository.findById(id).get());
        modelAndView.addObject(new Reservatie());
        return modelAndView;
    }

}
