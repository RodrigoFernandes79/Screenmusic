package br.com.example.challenge.screenMusic.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private ArtistType artistType;
    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Albums> albums;

    private String artistDescription;

    public Artist() {

    }

    public Artist(String name, ArtistType artistType, String artistDescription) {
        this.name = name;
        this.artistType = artistType;
        this.albums = new HashSet<>();
        this.artistDescription = artistDescription;
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

    public ArtistType getArtistType() {
        return artistType;
    }

    public void setArtistType(ArtistType artistType) {
        this.artistType = artistType;
    }

    public Set<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Albums> albums) {
        this.albums = albums;
    }

    public String getArtistDescription() {
        return artistDescription;
    }

    public void setArtistDescription(String artistDescription) {
        this.artistDescription = artistDescription;
    }

    @Override
    public String toString() {
        return
                "Artist " + name + '\'' +
                        " artistType: " + artistType +
                        " Bibliography: " + artistDescription;

    }
}
