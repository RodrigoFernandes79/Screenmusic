package br.com.example.challenge.screenMusic.repository;

import br.com.example.challenge.screenMusic.model.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Albums, Long> {
    Optional<Albums> findByAlbumName(String albumName);
}
