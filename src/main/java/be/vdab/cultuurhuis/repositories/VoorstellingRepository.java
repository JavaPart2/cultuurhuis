package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoorstellingRepository extends JpaRepository<Voorstelling, Long> {
    List<Voorstelling> findByGenre(Genre genre);
}
