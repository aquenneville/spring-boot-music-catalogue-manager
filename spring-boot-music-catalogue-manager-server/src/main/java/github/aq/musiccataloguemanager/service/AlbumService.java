package github.aq.musiccataloguemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.persistence.AlbumMapper;
import github.aq.musiccataloguemanager.persistence.ArtistMapper;

public class AlbumService {

	@Autowired
	AlbumMapper albumMapper;
	
	@Autowired
	ArtistMapper artistMapper;
	
	public Album selectById(Album album) {
		return albumMapper.selectById(album.getId());
	}
	
	public long insert(Album album) {
		Artist artist = artistMapper.selectById(album.getArtist().getId());
		if (artist != null) {  
			return albumMapper.insert(album);
		}
		return 0;
	}
	
	public long update(Album album) {
		Album storedAlbum = albumMapper.selectById(album.getId());
		if (storedAlbum != null) {
			// merge albums values ?
			return albumMapper.update(album);
		}
		return 0;
	}
	
	public long delete(Album album) {
		Album storedAlbum = albumMapper.selectById(album.getId());
		if (storedAlbum != null) {
			return albumMapper.delete(album);
		}
		return 0;
	}
	
	public List<Album> getAllAlbums() {
		return albumMapper.selectAll();		
	}
}
