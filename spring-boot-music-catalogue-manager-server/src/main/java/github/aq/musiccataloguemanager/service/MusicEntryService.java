package github.aq.musiccataloguemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.persistence.MusicEntryMapper;

public class MusicEntryService {
	
	@Autowired
	MusicEntryMapper locationMapper;
	
	public MusicEntry selectById(MusicEntry location) {
		return locationMapper.selectById(location.getId());
	}
	
	public long insert(MusicEntry location) {
		return locationMapper.insert(location);
	}
	
	public long update(MusicEntry location) {
		MusicEntry storedLocation = locationMapper.selectById(location.getId());
		if (storedLocation != null) {
			// merge albums values ?
			return locationMapper.update(storedLocation);
		}
		return 0;
	}
	
	public long delete(MusicEntry location) {
		MusicEntry storedLocation = locationMapper.selectById(location.getId());
		if (storedLocation != null) {
			return locationMapper.delete(location);
		}
		return 0;
	}
	
	public List<MusicEntry> getAllLocations() {
		return locationMapper.selectAll();
	}
}
