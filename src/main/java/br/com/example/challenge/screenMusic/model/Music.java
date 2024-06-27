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
    @Transient
    private Albums albums;

    public Music() {

    }

    public Music(Long id, String name, Integer durationInMinutes, Albums albums) {
        this.id = id;
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.albums = albums;
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

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", albums=" + albums +
                '}';
    }
}
