package com.owr.ex.spring.vote.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminAccessService {

	@Autowired
	private HttpServletRequest request;

	@Value("${adminip}")
	private String adminip;

	public boolean amIAdmin() {
		return adminip.endsWith(request.getRemoteAddr());
	}

}
