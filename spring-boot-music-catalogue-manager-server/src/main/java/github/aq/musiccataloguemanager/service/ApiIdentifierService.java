package github.aq.musiccataloguemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.ApiIdentifier;
import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.persistence.AlbumMapper;
import github.aq.musiccataloguemanager.persistence.ApiIdentifierMapper;
import github.aq.musiccataloguemanager.persistence.ArtistMapper;

public class ApiIdentifierService {
	
	@Autowired
	ApiIdentifierMapper apiIdentifierMapper;
	
	public ApiIdentifier selectById(ApiIdentifier apiIdentifier) {
		return apiIdentifierMapper.selectById(apiIdentifier.getId());
	}
	
	public long insert(ApiIdentifier apiIdentifier) {
		return apiIdentifierMapper.insert(apiIdentifier);
	}
	
	public long update(ApiIdentifier apiIdentifier) {
		ApiIdentifier storedArtist = apiIdentifierMapper.selectById(apiIdentifier.getId());
		if (storedArtist != null) {
			// merge albums values ?
			return apiIdentifierMapper.update(storedArtist);
		}
		return 0;
	}
	
	public long delete(ApiIdentifier apiIdentifier) {
		ApiIdentifier storedApiId = apiIdentifierMapper.selectById(apiIdentifier.getId());
		if (storedApiId != null) {
			return apiIdentifierMapper.delete(apiIdentifier);
		}
		return 0;
	}
	
	public List<ApiIdentifier> getAllApiIdentifiers() {
		return apiIdentifierMapper.selectAll();		
	}
}
