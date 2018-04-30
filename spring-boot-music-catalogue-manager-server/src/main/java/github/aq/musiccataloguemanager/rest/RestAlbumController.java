package github.aq.musiccataloguemanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.service.AlbumService;

@RestController
@RequestMapping("/api/album")
public class RestAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Album> getAllAlbums() {
		return albumService.getAllAlbums();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Album selectAlbum(@PathVariable("Id") String id) {
		Album album = new Album();
		album.setId(Long.valueOf(id)); 
		return albumService.selectById(album);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteAlbum(@PathVariable("Id") String id) {
		Album album = new Album();
		album.setId(Long.valueOf(id)); 
		if (albumService.delete(album) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectAlbum(@RequestBody Album album) {		
		if (albumService.insert(album) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/album")
	public String updateAlbum(@RequestBody Album album) {
		if (albumService.update(album) > 0) {
			return "OK";
		}
		return "FAILED";
	}

}
