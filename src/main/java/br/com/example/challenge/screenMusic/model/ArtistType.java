package br.com.example.challenge.screenMusic.model;

public enum ArtistType {

    SOLO("Solo"),
    DUO("Duo"),
    BAND("Band");

    private String typeArtist;

    ArtistType(String typeArtist) {
        this.typeArtist = typeArtist;
    }
}
