package br.com.example.challenge.screenMusic.model;

import jakarta.persistence.*;

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
    private Integer releaseYear;
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Music> musics;
    @ManyToOne
    private Artist artist;

    public Albums() {

    }

    public Albums(String albumName, Integer releaseYear, Artist artist) {
        this.albumName = albumName;
        this.releaseYear = releaseYear;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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
                ", releaseYear=" + releaseYear +
                ", musics=" + musics +
                ", artist=" + artist.getName() +
                '}';
    }
}
