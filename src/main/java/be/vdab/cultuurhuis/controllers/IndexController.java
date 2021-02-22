package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.repositories.GenreRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final GenreRepository genreRepository;
    private final VoorstellingRepository voorstellingRepository;

    public IndexController(GenreRepository genreRepository, VoorstellingRepository voorstellingRepository) {
        this.genreRepository = genreRepository;
        this.voorstellingRepository = voorstellingRepository;
    }

    @GetMapping
    public ModelAndView welkom(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("genres", genreRepository.findAllOrderByNaam());
        return modelAndView;
    }

    @GetMapping("/genres/{id}")
    public ModelAndView toonVoorstellingen(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("genres", genreRepository.findAllOrderByNaam());
        Genre gekozenGenre = genreRepository.findById(id).get();
        modelAndView.addObject("gekozengenre", gekozenGenre.getNaam());
        modelAndView.addObject("voorstellingen", voorstellingRepository.findByGenre(gekozenGenre));
        return modelAndView;
    }
}
