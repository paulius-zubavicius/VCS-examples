package com.owr.ex.spring.vote.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owr.ex.spring.vote.model.VoteResult;
import com.owr.ex.spring.vote.model.Voting;
import com.owr.ex.spring.vote.model.VotingSummarize;
import com.owr.ex.spring.vote.repository.VotingRepository;

@Service
public class VotingService {

	@Autowired
	private VotingRepository repo;

	public List<VotingSummarize> listVotings() {

		List<VotingSummarize> v = new ArrayList<>();
		for (Voting voting : repo.listAll()) {
			v.add(new VotingSummarize(voting));
		}

		return v;
	}

	public Voting showVoting(String votingId) {
		Voting v = null;
		if (repo.isExists(votingId)) {
			v = repo.get(votingId);
		}
		return v;
	}

	// FIXME syncronize
	public VoteResult vote(String theVoter, String votingId, Integer choise) {

		if (!repo.isExists(votingId)) {
			return VoteResult.Vote_not_found;
		}
		Voting v = repo.get(votingId);

		if (v.getVoters().contains(theVoter)) {
			return VoteResult.Already_voted;
		}

		// Already voted
		v.getVoters().add(theVoter);
		// count vote
		v.getOptions().get(choise).vote();

		return VoteResult.Ok;

	}

}
