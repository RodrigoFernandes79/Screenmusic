package br.com.example.challenge.screenMusic.repository;

import br.com.example.challenge.screenMusic.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByNameContainingIgnoreCase(String artistName);
}
