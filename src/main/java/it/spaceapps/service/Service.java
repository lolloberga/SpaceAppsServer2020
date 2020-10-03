package it.spaceapps.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.spaceapps.dto.Header;
import it.spaceapps.manager.GenericManager;
import it.spaceapps.manager.ObservatoriesManager;

@RestController
@RequestMapping("/v1")
public class Service {

	@Autowired
	GenericManager genericManager;
	
	@Autowired
	ObservatoriesManager obsManager;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> isAlive(HttpServletRequest request) {
		return new ResponseEntity<String>(genericManager.isAlive(new Header(request), "storeservice").toString(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/observatories", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getObservatories() {
		return new ResponseEntity<String>(obsManager.getObservatories(), HttpStatus.OK);
	}
}
