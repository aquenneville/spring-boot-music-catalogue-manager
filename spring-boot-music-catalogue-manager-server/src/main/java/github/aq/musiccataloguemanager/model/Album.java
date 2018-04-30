package github.aq.musiccataloguemanager.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Album { // Release

	private long id;
	private String name;
	private Artist artist;
	private Path cataloguePath;
	private LocalDate releaseDate;
	private List<Song> songs;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public Path getCataloguePath() {
		return cataloguePath;
	}
	public void setCataloguePath(Path cataloguePath) {
		this.cataloguePath = cataloguePath;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
}
