package github.aq.musiccataloguemanager.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MusicEntry {

	private long id;
	private Path cataloguePath;
	private Artist artist;
	private Album album;
	private Song song;
	private String originName;
	private Path pathOrigin;
	private Path parentPathOrigin;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Path getPathOrigin() {
		return pathOrigin;
	}
	public void setPathOrigin(String pathOrigin) {
		this.pathOrigin = Paths.get(pathOrigin);
	}
	public Path getCataloguePath() {
		return cataloguePath;
	}
	public void setCataloguePath(Path cataloguePath) {
		this.cataloguePath = cataloguePath;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getOriginName() {
		return originName;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public Album getAlbum() {
		return album;
	}
	public void setSong(Song song) {
		this.song = song;		
	}
	
	public Song getSong() {
		return song;
	}
	public Path getParentPathOrigin() {
		return parentPathOrigin;
	}
	public void setParentPathOrigin(Path parentPathOrigin) {
		this.parentPathOrigin = parentPathOrigin;
	}
	public void setPathOrigin(Path pathOrigin) {
		this.pathOrigin = pathOrigin;
	}
	
	
}
