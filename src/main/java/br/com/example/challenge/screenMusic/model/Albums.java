package br.com.example.challenge.screenMusic.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String albumName;
    private LocalDate releaseDate;
    @Transient
    private List<Music> musics;
    @Transient
    private Artist artist;

    public Albums() {

    }

    public Albums(String albumName, LocalDate releaseDate, Artist artist) {
        this.albumName = albumName;
        this.releaseDate = releaseDate;
        this.musics = new ArrayList<>();
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "albumName='" + albumName + '\'' +
                ", releaseDate=" + releaseDate +
                ", musics=" + musics +
                ", artist=" + artist +
                '}';
    }
}
