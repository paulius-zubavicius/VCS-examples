package com.owr.ex.spring.vote.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.owr.ex.spring.vote.model.Voting;
import com.owr.ex.spring.vote.services.VotingAdminService;

/**
 * <li>root/ <- welcome</li>
 * <li>root/add <- prideda baldsavima, arba uzraso ant virsaus</li>
 * <li>root/votingIp/stop <- uzdaro balsavima</li>
 * <li>root/votingIp/reset <-perkuria is naujo</li>
 * 
 * 
 */

@RestController
@RequestMapping("/root")
public class AdminController {

	private final static Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private VotingAdminService service;

	@GetMapping
	public String hello() {
		if (LOG.isInfoEnabled()) {
			LOG.info("hello");
		}
		return "{\"msg\" = \"I'm alive\"}";
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Void> create(@RequestBody Voting voting) {
		if (LOG.isInfoEnabled()) {
			LOG.info("create");
		}
		service.createVoting(voting);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{votingId}/stop")
	public ResponseEntity<Void> stop(@PathVariable("votingId") String votingId) {
		if (LOG.isInfoEnabled()) {
			LOG.info("stop");
		}
		service.openCloseVoting(votingId, false);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{votingId}/reset")
	public ResponseEntity<Void> reset(@PathVariable("votingId") String votingId) {
		if (LOG.isInfoEnabled()) {
			LOG.info("reset");
		}
		service.reset(votingId);
		return ResponseEntity.ok().build();
	}

}
