package br.com.example.challenge.screenMusic.principalMenu;

import br.com.example.challenge.screenMusic.model.Artist;
import br.com.example.challenge.screenMusic.model.ArtistType;
import br.com.example.challenge.screenMusic.repository.ArtistRepository;
import br.com.example.challenge.screenMusic.service.GeminiAPIService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner typeScanner = new Scanner(System.in);
    private boolean quitMenu = false;

    private ArtistRepository artistRepository;
    private GeminiAPIService geminiAPIService;

    public Menu(ArtistRepository artistRepository, GeminiAPIService geminiAPIService) {
        this.artistRepository = artistRepository;
        this.geminiAPIService = geminiAPIService;
    }

    public void showMenu() {
        while (!quitMenu) {
            System.out.println("""
                    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                    SCREENMUSIC - THE BEST OF MUSICS IS HERE!
                    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                        
                    Please, enter one option:
                    1 - Register an artist
                    2 - Show Artist by Name
                    0 - Quit
                    """);
            var options = typeScanner.nextInt();
            switch (options) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    showArtistsByName();
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

    public void registerArtist() {
        while (!quitMenu) {
            System.out.println("Please type the name of artist:");
            typeScanner.nextLine();
            var artistName = typeScanner.nextLine();

            System.out.println("""
                    Please choice the artist category
                    *********************************
                    1 - Solo
                    2 - Duo
                    3 - Band
                    """);
            var typeArtistChoice = typeScanner.nextInt();

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

            }
            Artist artist = new Artist();
            artist.setArtistType(ArtistType.valueOf(ArtistType.class, typeArtist));
            artist.setName(artistName);
            try {
                artist.setArtistDescription(geminiAPIService.textInputGeminiAI(artistName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Artist artistObject = artistRepository.save(artist);
            System.out.println(artist);
            System.out.println("Artist " + artistObject.getName() + " saved successfully");

            System.out.println();
            System.out.println("Do you want to register another artist? y/n?");
            var registerAgain = typeScanner.next().charAt(0);
            if (String.valueOf(registerAgain).equalsIgnoreCase("n")) {
                break;
            }

        }
    }

    private void showArtistsByName() {
        List<Artist> artistOptional = artistRepository.findAll();
        if (artistOptional.isEmpty()) {
            System.out.println("No artist was added in the playlist");
        }
        artistOptional.forEach(System.out::println);
    }
}

