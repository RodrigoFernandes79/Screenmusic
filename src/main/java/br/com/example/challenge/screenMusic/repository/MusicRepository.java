package br.com.example.challenge.screenMusic.repository;

import br.com.example.challenge.screenMusic.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music,Long> {
}
