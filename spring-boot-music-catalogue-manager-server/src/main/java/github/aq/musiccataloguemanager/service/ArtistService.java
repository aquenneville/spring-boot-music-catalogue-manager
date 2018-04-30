package github.aq.musiccataloguemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.persistence.ArtistMapper;

public class ArtistService {
	
	@Autowired
	ArtistMapper artistMapper;

	public Artist requestArtistFromSpotify() {
		return new Artist();
	}
	
	public Artist selectById(Artist artist) {
		return artistMapper.selectById(artist.getId());
	}
	
	public long insert(Artist artist) {
		return artistMapper.insert(artist);
	}
	
	public long update(Artist artist) {
		Artist storedArtist = artistMapper.selectById(artist.getId());
		if (storedArtist != null) {
			// merge albums values ?
			return artistMapper.update(storedArtist);
		}
		return 0;
	}
	
	public long delete(Artist artist) {
		Artist storedArtist = artistMapper.selectById(artist.getId());
		if (storedArtist != null) {
			return artistMapper.delete(artist);
		}
		return 0;
	}

	public List<Artist> getAllArtists() {
		return artistMapper.selectAll();		
	}
}
