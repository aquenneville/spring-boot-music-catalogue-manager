package github.aq.musiccataloguemanager.model;

import java.nio.file.Path;

public class Song { // track

	private long id;
	private Album album;
	private int length;
	private Path pathOrigin;
	private Path catalogueFilePath;
	private String title;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Path getPathOrigin() {
		return pathOrigin;
	}
	public void setPathOrigin(Path pathOrigin) {
		this.pathOrigin = pathOrigin;
	}
	public Path getCatalogueFilePath() {
		return catalogueFilePath;
	}
	public void setCatalogueFilePath(Path catalogueFilePath) {
		this.catalogueFilePath = catalogueFilePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
