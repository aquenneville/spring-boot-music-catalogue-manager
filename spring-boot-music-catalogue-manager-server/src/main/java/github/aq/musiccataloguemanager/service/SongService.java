package github.aq.musiccataloguemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.Song;
import github.aq.musiccataloguemanager.persistence.AlbumMapper;
import github.aq.musiccataloguemanager.persistence.SongMapper;

public class SongService {
	
	@Autowired
	AlbumMapper albumMapper;
	
	@Autowired
	SongMapper songMapper;
	
	public Song selectById(Song song) {
		return songMapper.selectById(song.getId());
	}
	
	public long insert(Song song) {
		Album album = albumMapper.selectById(song.getAlbum().getId());
		if (album == null) {
			return songMapper.insert(song);
		}
		return 0;
		
	}
	
	public long update(Song song) {
		Album storedAlbum = albumMapper.selectById(song.getAlbum().getId());
		if (storedAlbum != null) {
			// merge albums values ?
			return songMapper.update(song);
		}
		return 0;
	}
	
	public long delete(Song song) {
		Song storedSong = songMapper.selectById(song.getId());
		if (storedSong != null) {
			return songMapper.delete(storedSong);
		}
		return 0;
	}

	public List<Song> getAllSongs() {
		return songMapper.selectAll();
	}
}
