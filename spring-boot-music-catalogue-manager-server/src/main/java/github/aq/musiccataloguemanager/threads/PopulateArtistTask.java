package github.aq.musiccataloguemanager.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.parser.ArtistParser;
import github.aq.musiccataloguemanager.persistence.ArtistMapper;
import github.aq.musiccataloguemanager.persistence.MusicEntryMapper;
import github.aq.musiccataloguemanager.service.HttpClientService;

public class PopulateArtistTask implements Runnable {

	private String artistName;
	
	@Autowired
	ArtistMapper artistMapper;
	
	@Autowired
	MusicEntryMapper locationMapper;
	
	public PopulateArtistTask(String artistName) {
		this.artistName = artistName;
	}

	@Override
	public void run() {

		// request api: if the request is successfull 200 then => persist in database
		// http://www.artfulsoftware.com/infotree/queries.php
		String artistPath = new File(artistName).getName();
		String url = "https://musicbrainz.org/ws/2/artist/?query="+artistName+"&fmt=json";
		String responseContent = HttpClientService.doGetRequest(url);
		ArtistParser parser = new ArtistParser();
		List<Artist> artists = null;
		try {
			artists = parser.parseMusicbrainzArtists(responseContent);
			for (Artist artist: artists) {
				long successArtist = artistMapper.insert(artist);
				if (successArtist > 0) {						
					System.out.println("Artist: " + artistName + " inserted.");
				}						
			} 
			if (artists.size() > 1) {
				for (Artist artist: artists) {
					JaroWinklerDistance jwDistance = new JaroWinklerDistance();
					if (jwDistance.apply(artistPath, artist.getName()) >= 0.90) {
						MusicEntry location = new MusicEntry();
						location.setPathOrigin(Paths.get(artistName));
						location.setOriginName(artistPath);
						//http://www.artfulsoftware.com/infotree/queries.php
						location.setArtist(artist);
						long successLocation = locationMapper.insert(location);
						if (successLocation > 0) {						
							System.out.println("Location: " + location.getPathOrigin() + " inserted.");
						}
						break;
					}
				}
			} else {
				MusicEntry location = new MusicEntry();
				location.setPathOrigin(Paths.get(artistName));
				location.setOriginName(artistPath);
				//http://www.artfulsoftware.com/infotree/queries.php
				location.setArtist(artists.get(0));
				long successLocation = locationMapper.insert(location);
				if (successLocation > 0) {						
					System.out.println("Location: " + location.getPathOrigin() + " inserted.");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		//   notify change success
		// else report in file	
		//   notify change failure
			
		
	}
	
}
