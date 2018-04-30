package github.aq.musiccataloguemanager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.service.ETLService;

@RestController
@RequestMapping("/api")
public class RestETLController {
	
	@Autowired
	private ETLService etlService;
		
	@RequestMapping(method = RequestMethod.GET, value = "/populate-artists")
	public String populateArtists() {
		return etlService.triggerPopulateArtistDatabaseProcess();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/populate-albums")
	public String populateAlbums() {
		return etlService.triggerPopulateAlbumDatabaseProcess();
	}
	
	// endpoint for song population
	@RequestMapping(method = RequestMethod.GET, value = "/populate-songs")
	public String populateSongs() {
		return etlService.triggerPopulateSongsDatabaseProcess();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/create-new-music-library")
	public String createNewMusicLibrary() {
		return etlService.createNewMusicLibrary();
	}
	
	// import-folder-into-catalogue
}
