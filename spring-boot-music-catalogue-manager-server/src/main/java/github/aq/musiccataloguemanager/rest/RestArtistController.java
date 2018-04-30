package github.aq.musiccataloguemanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.service.ArtistService;

@RestController
@RequestMapping("/api/artist")
public class RestArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Artist> getAllArtists() {
		return artistService.getAllArtists();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Artist selectArtist(@PathVariable("Id") String id) {
		Artist artist = new Artist();
		artist.setId(Long.valueOf(id)); 
		return artistService.selectById(artist);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteArtist(@PathVariable("Id") String id) {
		Artist artist = new Artist();
		artist.setId(Long.valueOf(id)); 
		if (artistService.delete(artist) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectArtist(@RequestBody Artist artist) {		
		if (artistService.insert(artist) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateArtist(@RequestBody Artist artist) {
		if (artistService.update(artist) > 0) {
			return "OK";
		}
		return "FAILED";
	}

}
