package br.com.example.challenge.screenMusic.repository;

import br.com.example.challenge.screenMusic.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
