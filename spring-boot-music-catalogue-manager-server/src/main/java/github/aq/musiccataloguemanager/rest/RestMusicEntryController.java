package github.aq.musiccataloguemanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.service.MusicEntryService;

@RestController
@RequestMapping("/api/location")
public class RestMusicEntryController {
	
	@Autowired
	private MusicEntryService locationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<MusicEntry> getAllLocations() {
		return locationService.getAllLocations();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public MusicEntry selectArtist(@PathVariable("Id") String id) {
		MusicEntry location = new MusicEntry();
		location.setId(Long.valueOf(id)); 
		return locationService.selectById(location);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteArtist(@PathVariable("Id") String id) {
		MusicEntry location = new MusicEntry();
		location.setId(Long.valueOf(id)); 
		if (locationService.delete(location) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectArtist(@RequestBody MusicEntry location) {		
		if (locationService.insert(location) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateStudent(@RequestBody MusicEntry location) {
		if (locationService.update(location) > 0) {
			return "OK";
		}
		return "FAILED";
	}

}
