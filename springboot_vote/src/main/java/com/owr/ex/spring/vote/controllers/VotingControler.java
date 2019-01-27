package com.owr.ex.spring.vote.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.owr.ex.spring.vote.model.VoteResult;
import com.owr.ex.spring.vote.model.Voting;
import com.owr.ex.spring.vote.model.VotingSummarize;
import com.owr.ex.spring.vote.services.VotingService;

/***
 * <li>voting/ <- rodo visus voting'us</li>
 * <li>voting/votingId <- rodo rezultata ir galimus pasirinkimus</li>
 * <li>voting/votingId/vote <-siuncia balsa</li>
 * 
 * 
 */
@RestController
@RequestMapping("/voting")
public class VotingControler {

	private final static Logger LOG = LoggerFactory.getLogger(VotingControler.class);

	@Autowired
	private VotingService service;

	@GetMapping
	public List<VotingSummarize> listAll() {
		if (LOG.isInfoEnabled()) {
			LOG.info("listAll");
		}
		return service.listVotings();
	}

	@GetMapping(value = "/{votingId}")
	public Voting showVoting(@PathVariable("votingId") String votingId) {
		if (LOG.isInfoEnabled()) {
			LOG.info("showVoting");
		}
		return service.showVoting(votingId);
	}

	@GetMapping(value = "/{votingId}/{choice}")
	public VoteResult vote(@PathVariable("votingId") String votingId, @PathVariable("choice") Integer choice,
			HttpServletRequest request) {
		if (LOG.isInfoEnabled()) {
			LOG.info("vote");
		}
		return service.vote(request.getRemoteAddr(), votingId, choice);
	}

}
