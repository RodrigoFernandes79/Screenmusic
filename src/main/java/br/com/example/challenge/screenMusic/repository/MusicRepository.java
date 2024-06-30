package br.com.example.challenge.screenMusic.repository;

import br.com.example.challenge.screenMusic.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("""
            SELECT m FROM Music m JOIN m.album s JOIN s.artist a WHERE a.name ILIKE %:artistName% 
            """)
    List<Music> findByArtistName(String artistName);
}
