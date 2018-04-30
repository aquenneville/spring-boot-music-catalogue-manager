package github.aq.musiccataloguemanager.model;

public class ApiIdentifier {
	
	private long id;
	private String apiName;
	private String value;
	private Artist artist;
	private Album album;
    private Song song;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
}
