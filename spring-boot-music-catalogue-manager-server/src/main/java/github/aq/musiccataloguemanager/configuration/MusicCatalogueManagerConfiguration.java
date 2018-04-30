package github.aq.musiccataloguemanager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("musiccataloguemanager")
public class MusicCatalogueManagerConfiguration {

	private String pathOriginRoot = "";
	private String pathCatalogue = "";
	
	public String getPathOriginRoot() {
		return pathOriginRoot;
	}
	public void setPathOriginRoot(String pathOriginRoot) {
		this.pathOriginRoot = pathOriginRoot;
	}
	public String getPathCatalogue() {
		return pathCatalogue;
	}
	public void setPathCatalogue(String pathCatalogue) {
		this.pathCatalogue = pathCatalogue;
	}
	
	
}
