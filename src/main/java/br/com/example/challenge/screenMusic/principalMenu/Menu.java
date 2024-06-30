package br.com.example.challenge.screenMusic.principalMenu;

import br.com.example.challenge.screenMusic.model.Albums;
import br.com.example.challenge.screenMusic.model.Artist;
import br.com.example.challenge.screenMusic.model.ArtistType;
import br.com.example.challenge.screenMusic.model.Music;
import br.com.example.challenge.screenMusic.repository.AlbumRepository;
import br.com.example.challenge.screenMusic.repository.ArtistRepository;
import br.com.example.challenge.screenMusic.repository.MusicRepository;
import br.com.example.challenge.screenMusic.service.GeminiAPIService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Scanner typeScanner = new Scanner(System.in);
    private boolean quitMenu = false;
    Artist artist;
    private ArtistRepository artistRepository;
    Albums album;
    private GeminiAPIService geminiAPIService;
    private AlbumRepository albumRepository;
    private MusicRepository musicRepository;

    public Menu(ArtistRepository artistRepository,
                GeminiAPIService geminiAPIService,
                AlbumRepository albumRepository,
                MusicRepository musicRepository) {
        this.artistRepository = artistRepository;
        this.geminiAPIService = geminiAPIService;
        this.albumRepository = albumRepository;
        this.musicRepository = musicRepository;
    }

    public void showMenu() {
        while (!quitMenu) {
            System.out.println("""
                    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                    SCREENMUSIC - THE BEST OF MUSICS IS HERE!
                    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                        
                    Please, enter one option:
                    1 - Register an artist
                    2 - Show Artists of your Playlist
                    3 - Register Music
                    4 - Show Music List
                    0 - Quit
                    """);
            var options = typeScanner.nextInt();
            typeScanner.nextLine();
            switch (options) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    showArtistsList();
                    break;
                case 3:
                    registerMusic();
                    break;
                case 4:
                    showMusicList();
                    break;
                case 0:
                    System.out.println("The program will finish!!!See you later!");
                    quitMenu = true;
                    break;
                default:
                    System.out.println("This option not exist! choose another option");
            }
        }

    }

    private void registerArtist() {
        while (!quitMenu) {
            System.out.println("Please type the name of artist:");
            var artistName = typeScanner.nextLine();

            System.out.println("""
                    Please choice the artist category
                    *********************************
                    1 - Solo
                    2 - Duo
                    3 - Band
                    """);
            var typeArtistChoice = typeScanner.nextInt();
            typeScanner.nextLine();
            var typeArtist = "";
            switch (typeArtistChoice) {
                case 1:
                    typeArtist = "SOLO";
                    break;
                case 2:
                    typeArtist = "DUO";
                    break;
                case 3:
                    typeArtist = "BAND";
                    break;
                default:
                    System.out.println("Wrong option! please choose another option");
                    continue;

            }

            artist = new Artist();
            artist.setArtistType(ArtistType.valueOf(ArtistType.class, typeArtist));
            artist.setName(artistName);
            try {
                artist.setArtistDescription(geminiAPIService.textInputGeminiAI(artistName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Artist artistObject = artistRepository.save(artist);

            System.out.println("Artist " + artistObject.getName() + " saved successfully");

            System.out.println("Do you want to register another artist? y/n?");
            var registerAgain = typeScanner.next().charAt(0);
            typeScanner.nextLine();
            if (String.valueOf(registerAgain).equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private void showArtistsList() {
        List<Artist> artistOptional = artistRepository.findAll();
        if (artistOptional.isEmpty()) {
            System.out.println("No artist was added in the playlist");
        }
        artistOptional.forEach(System.out::println);
    }

    private void registerMusic() {
        System.out.println("Please enter the Artist Name: ");
        var artistName = typeScanner.nextLine();
        Optional<Artist> artistFound = artistRepository
                .findByNameContainingIgnoreCase(artistName);
        if (!artistFound.isPresent()) {
            System.out.println("Artist not found in Screenmusic. Register:");
            registerArtist();
            artistFound = artistRepository
                    .findByNameContainingIgnoreCase(artistName);
        }

        System.out.println("Please enter the Album name:");
        var albumName = typeScanner.nextLine();

        Optional<Albums> albumFind = albumRepository.findByAlbumName(albumName);

        if (albumFind.isEmpty()) {

            try {
                var releaseYearAI = geminiAPIService.insertYearOfAlbumInGeminiAI(artistFound.get().getName(), albumName);
                album = new Albums(albumName, releaseYearAI, artistFound.get());
                Albums albumObject = albumRepository.save(album);
                System.out.println("Album " + albumObject.getAlbumName() + " saved successfully!");
                System.out.println(albumObject);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        albumFind = albumRepository.findByAlbumName(albumName);

        System.out.println("Please enter the Music name: ");
        var musicName = typeScanner.nextLine();
        try {
            var artist = albumFind.get().getArtist().getName();
            var durationInMinutesAI = geminiAPIService.insertMinutesOfMusicInGeminiAI(musicName, artist);
            Music music = new Music(musicName, durationInMinutesAI, albumFind.get());
            Music musicObject = musicRepository.save(music);

            System.out.println("Music " + musicObject.getName() + " saved Successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void showMusicList() {
        List<Music> musics = musicRepository.findAll();
        musics.stream()
                .sorted(Comparator.comparing(a -> a.getAlbum().getArtist().getName()))
                .forEach(m ->
                        System.out.println("Music: " + m.getName() + " Artist: " + m.getAlbum().getArtist().getName())
                );
        System.out.println();
    }
}



