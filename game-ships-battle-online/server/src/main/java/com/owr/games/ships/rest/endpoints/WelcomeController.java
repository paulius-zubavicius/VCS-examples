package com.owr.games.ships.rest.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class WelcomeController {

	@Autowired
	private HttpServletRequest request;

	@GetMapping
	public String sayHi() {
		//request.getLocale();
		return "<html><body><a href='http://" + request.getLocalName() + ":" + request.getLocalPort()
				+ "/swagger-ui.html'>Swagger UI</a></body></html>";
	}

}
