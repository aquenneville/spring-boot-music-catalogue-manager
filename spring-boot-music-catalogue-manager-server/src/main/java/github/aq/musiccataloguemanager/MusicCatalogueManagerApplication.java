package github.aq.musiccataloguemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicCatalogueManagerApplication {

	//https://musicbrainz.org/ws/2/artist/?query=ac%5C%2Fdc&fmt=json
	//https://musicbrainz.org/ws/2/artist/?query=deep%20purple&fmt=json
	public static void main(String[] args) {
		SpringApplication.run(MusicCatalogueManagerApplication.class, args);
	}
}
