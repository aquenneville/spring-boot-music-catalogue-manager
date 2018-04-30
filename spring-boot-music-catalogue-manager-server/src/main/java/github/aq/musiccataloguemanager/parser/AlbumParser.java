package github.aq.musiccataloguemanager.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.ApiIdentifier;
import github.aq.musiccataloguemanager.model.Artist;

public class AlbumParser {
	
	public List<Album> parseAlbums(Artist artist, String jsonContent) throws IOException {
		List<Album> artists = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(jsonContent);
		
		JsonNode artistNode=rootNode.path("artists");
		Iterator<JsonNode> elements = artistNode.elements();
		String apiId = "";
		String apiArtistName = "";
		String apiScore = "";
		while (elements.hasNext()) {
			 JsonNode artistChildNode = elements.next();
			 if (artistChildNode.has("id")) {
				 System.out.println(artistChildNode.get("id").textValue());
				 apiId = artistChildNode.get("id").textValue();
			 }
			 if (artistChildNode.has("name")) {
				 System.out.println(artistChildNode.get("id").textValue());
				 apiArtistName = artistChildNode.get("id").textValue();
			 }
			 if (artistChildNode.has("score")) {
				 System.out.println(artistChildNode.get("score").textValue());
				 apiScore = artistChildNode.get("score").textValue();
			 }			 
			 if (apiId.length() > 0 && apiArtistName.length() > 0 && "100".equals(apiScore)) {
				 ApiIdentifier apiIdentifier = new ApiIdentifier();
				 apiIdentifier.setValue(apiId);
				 apiIdentifier.setApiName("MUSICBRAINZ");
				 Album musicBrainzArtist = new Album();
				 musicBrainzArtist.setArtist(artist);
				 musicBrainzArtist.setName(apiArtistName);
				 artists.add(musicBrainzArtist);
			 }
		}
		return artists;
        
	}
	
}
