package github.aq.musiccataloguemanager.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.parser.AlbumParser;
import github.aq.musiccataloguemanager.parser.ArtistParser;
import github.aq.musiccataloguemanager.persistence.AlbumMapper;
import github.aq.musiccataloguemanager.persistence.ArtistMapper;
import github.aq.musiccataloguemanager.persistence.MusicEntryMapper;
import github.aq.musiccataloguemanager.service.HttpClientService;

public class PopulateAlbumTask implements Runnable {

	private String sourceEntity;
	private Artist artist;
	
	@Autowired
	AlbumMapper albumMapper;
	
	@Autowired
	MusicEntryMapper locationDao;
	
	public PopulateAlbumTask(String sourceEntity, Artist artist) {
		this.sourceEntity = sourceEntity;
		this.artist = artist;
		
	}

	@Override
	public void run() {

		
		// request api 
		// if the request is successfull 200 then => persist in database
		// http://www.artfulsoftware.com/infotree/queries.php
		String entityName = new File(sourceEntity).getName();
		String url = "https://musicbrainz.org/ws/2/artist/?query="+entityName+"&fmt=json";
		String respContent = HttpClientService.doGetRequest(url);
		AlbumParser parser = new AlbumParser();
		List<Album> albums = null;
		try {
			albums = parser.parseAlbums(artist, respContent);
			for (Album album: albums) {
				long successAlbum = albumMapper.insert(album);
				if (successAlbum > 0) {						
				
				}						
			} 
			if (albums.size() > 1) {
				for (Album album: albums) {
					JaroWinklerDistance jwDistance = new JaroWinklerDistance();
					if (jwDistance.apply(entityName, artist.getName()) >= 0.90) {
						MusicEntry location = new MusicEntry();
						location.setPathOrigin(Paths.get(sourceEntity));
						location.setOriginName(entityName);
						//http://www.artfulsoftware.com/infotree/queries.php
						//location.setArtist(artist);
						location.setAlbum(album);
						long successLocation = locationDao.insert(location);
						break;
					}
				}
			} else {
				MusicEntry location = new MusicEntry();
				location.setPathOrigin(Paths.get(sourceEntity));
				location.setOriginName(entityName);
				//http://www.artfulsoftware.com/infotree/queries.php
				//location.setArtist(artist);
				location.setAlbum(albums.get(0));
				long successLocation = locationDao.insert(location);

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
