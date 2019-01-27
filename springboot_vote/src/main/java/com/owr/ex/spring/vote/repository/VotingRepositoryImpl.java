package com.owr.ex.spring.vote.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.owr.ex.spring.vote.model.VoteOption;
import com.owr.ex.spring.vote.model.Voting;

@Repository
public class VotingRepositoryImpl implements VotingRepository {

	private Map<String, Voting> pull = new HashMap<>();

	@Override
	public void add(Voting voting) {
		pull.put(voting.getVotingId(), voting);
	}

	@Override
	public boolean isExists(String votingId) {
		return pull.containsKey(votingId);
	}

	@Override
	public void remove(String votingId) {
		pull.remove(votingId);
	}

	@Override
	public List<Voting> listAll() {
		return new ArrayList<>(pull.values());
	}

	@Override
	public Voting get(String votingId) {
		return pull.get(votingId);
	}

	@Override
	public void setStatus(String votingId, boolean active) {
		pull.get(votingId).setActive(active);
	}

	@Override
	public void reset(String votingId) {

		Voting v = pull.get(votingId);
		v.setActive(true);
		for (VoteOption op : v.getOptions()) {
			op.setVotes(0);
		}
		v.getVoters().clear();

	}

}
