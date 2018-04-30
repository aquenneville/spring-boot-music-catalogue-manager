package github.aq.musiccataloguemanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.model.Song;
import github.aq.musiccataloguemanager.service.SongService;

@RestController
@RequestMapping("/api/song")
public class RestSongController {
	
	@Autowired
	private SongService songService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Song> getAllArtists() {
		return songService.getAllSongs();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Song selectArtist(@PathVariable("Id") String id) {
		Song song = new Song();
		song.setId(Long.valueOf(id)); 
		return songService.selectById(song);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteArtist(@PathVariable("Id") String id) {
		Song song = new Song();
		song.setId(Long.valueOf(id)); 
		if (songService.delete(song) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectArtist(@RequestBody Song song) {		
		if (songService.insert(song) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateStudent(@RequestBody Song song) {
		if (songService.update(song) > 0) {
			return "OK";
		}
		return "FAILED";
	}

}
