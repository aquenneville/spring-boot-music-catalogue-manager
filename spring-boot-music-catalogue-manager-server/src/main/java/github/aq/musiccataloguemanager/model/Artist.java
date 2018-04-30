package github.aq.musiccataloguemanager.model;

public class Artist {

	//artist id: https://musicbrainz.org/ws/2/artist/?query=acdc&fmt=json
	//albums: https://musicbrainz.org/ws/2/artist/66c662b6-6e2f-4930-8610-912e24c63ed1?inc=recordings&fmt=json
	//https://musicbrainz.org/ws/2/artist/66c662b6-6e2f-4930-8610-912e24c63ed1?inc=releases&fmt=json
	//https://musicbrainz.org/ws/2/release/46448299-a910-4313-ab3c-5a96451b1771?inc=tracks&fmt=json
	//https://musicbrainz.org/ws/2/release/46448299-a910-4313-ab3c-5a96451b1771?inc=recordings&fmt=json
	//https://musicbrainz.org/ws/2/artist?query=acdc&limit=10&offset=0&fmt=json get artists > score=100
	private long id;
	private String name;
	
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
	
}
