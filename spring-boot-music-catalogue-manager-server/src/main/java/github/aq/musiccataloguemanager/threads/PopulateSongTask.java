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
import github.aq.musiccataloguemanager.model.Song;
import github.aq.musiccataloguemanager.parser.AlbumParser;
import github.aq.musiccataloguemanager.parser.SongParser;
import github.aq.musiccataloguemanager.persistence.AlbumMapper;
import github.aq.musiccataloguemanager.persistence.MusicEntryMapper;
import github.aq.musiccataloguemanager.persistence.SongMapper;
import github.aq.musiccataloguemanager.service.HttpClientService;

public class PopulateSongTask implements Runnable {

	private String sourceEntity;
	private Album album;
	
	@Autowired
	AlbumMapper albumMapper;
	
	@Autowired
	SongMapper songMapper;
	
	@Autowired
	MusicEntryMapper locationDao;
	
	public PopulateSongTask(String sourceEntity, Album album) {
		this.sourceEntity = sourceEntity;
		this.album = album;
		
	}

	@Override
	public void run() {

		
		// request api 
		// if the request is successfull 200 then => persist in database
		// http://www.artfulsoftware.com/infotree/queries.php
		String entityName = new File(sourceEntity).getName();
		String url = "https://musicbrainz.org/ws/2/artist/?query="+entityName+"&fmt=json";
		String respContent = HttpClientService.doGetRequest(url);
		SongParser parser = new SongParser();
		List<Song> songs = null;
		//try {
			songs = parser.parseSongs(respContent);
			for (Song song: songs) {
				long successSong = songMapper.insert(song);
				if (successSong > 0) {						
				
				}						
			} 
			if (songs.size() > 1) {
				for (Song song: songs) {
					JaroWinklerDistance jwDistance = new JaroWinklerDistance();
					if (jwDistance.apply(entityName, song.getTitle()) >= 0.90) {
						MusicEntry location = new MusicEntry();
						location.setPathOrigin(Paths.get(sourceEntity));
						location.setOriginName(entityName);
						//http://www.artfulsoftware.com/infotree/queries.php
						//location.setAlbum(album);
						location.setSong(song);
						long successLocation = locationDao.insert(location);
						break;
					}
				}
			} else {
				MusicEntry location = new MusicEntry();
				location.setPathOrigin(Paths.get(sourceEntity));
				location.setOriginName(entityName);
				//http://www.artfulsoftware.com/infotree/queries.php
				//location.setArtist(album.getArtist());
				//location.setAlbum(album);
				location.setSong(songs.get(0));
				long successLocation = locationDao.insert(location);

			}
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}			
		//   notify change success
		// else report in file	
		//   notify change failure
			
		
	}
	
}
