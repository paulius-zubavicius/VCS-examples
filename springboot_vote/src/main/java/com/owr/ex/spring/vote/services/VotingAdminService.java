package com.owr.ex.spring.vote.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owr.ex.spring.vote.model.Voting;
import com.owr.ex.spring.vote.repository.VotingRepository;

@Service
public class VotingAdminService {

	@Autowired
	private VotingRepository repo;

	@Autowired
	private AdminAccessService admin;

	public void createVoting(Voting voting) {
		checkTheAccess();
		repo.add(voting);
	}

	public void openCloseVoting(String votingId, boolean open) {
		checkTheAccess();
		repo.setStatus(votingId, open);
	}

	public void removeVoting(String votingId) {
		checkTheAccess();
		repo.remove(votingId);
	}

	public void reset(String votingId) {
		repo.reset(votingId);
	}

	private void checkTheAccess() {
		if (!admin.amIAdmin()) {
			throw new RuntimeException();
		}
	}

}
