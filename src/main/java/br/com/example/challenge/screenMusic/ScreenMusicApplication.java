package br.com.example.challenge.screenMusic;

import br.com.example.challenge.screenMusic.principalMenu.Menu;
import br.com.example.challenge.screenMusic.repository.ArtistRepository;
import br.com.example.challenge.screenMusic.service.GeminiAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMusicApplication implements CommandLineRunner {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private GeminiAPIService geminiAPIService;

    public static void main(String[] args) {
        SpringApplication.run(ScreenMusicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu(artistRepository, geminiAPIService);
        menu.showMenu();
    }
}
