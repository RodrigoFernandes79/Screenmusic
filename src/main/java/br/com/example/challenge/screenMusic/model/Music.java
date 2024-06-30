package br.com.example.challenge.screenMusic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer durationInMinutes;
    @ManyToOne
    private Albums album;

    public Music() {

    }

    public Music(String name, Integer durationInMinutes, Albums album) {
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Albums getAlbum() {
        return album;
    }

    public void setAlbum(Albums album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", albums=" + album.getAlbumName() +
                '}';
    }
}
