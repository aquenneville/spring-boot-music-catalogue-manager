package github.aq.musiccataloguemanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import github.aq.musiccataloguemanager.model.ApiIdentifier;
import github.aq.musiccataloguemanager.service.ApiIdentifierService;

@RestController
@RequestMapping("/api/api-identifier")
public class RestApiIdentifierController {
	
	@Autowired
	private ApiIdentifierService apiIdentifierService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ApiIdentifier> getAllApiIdentifiers() {
		return apiIdentifierService.getAllApiIdentifiers();
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ApiIdentifier selectApiIdentifier(@PathVariable("Id") String id) {
		ApiIdentifier apiIdentifier = new ApiIdentifier();
		apiIdentifier.setId(Long.valueOf(id)); 
		return apiIdentifierService.selectById(apiIdentifier);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String deleteApiIdentifier(@PathVariable("Id") String id) {
		ApiIdentifier apiIdentifier = new ApiIdentifier();
		apiIdentifier.setId(Long.valueOf(id)); 
		if (apiIdentifierService.delete(apiIdentifier) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectApiIdentifier(@RequestBody ApiIdentifier apiIdentifier) {		
		if (apiIdentifierService.insert(apiIdentifier) > 0) {
			return "OK";
		}
		return "FAILED";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateApiIdentifier(@RequestBody ApiIdentifier apiIdentifier) {
		if (apiIdentifierService.update(apiIdentifier) > 0) {
			return "OK";
		}
		return "FAILED";
	}

}
