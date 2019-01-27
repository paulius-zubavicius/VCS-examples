package com.owr.ex.spring.vote.repository;

import java.util.List;

import com.owr.ex.spring.vote.model.Voting;
import com.owr.ex.spring.vote.model.VotingResult;

public interface VotingRepository {

	boolean isExists(String votingId);

	Voting get(String votingId);

	void add(Voting voting);

	void remove(String votingId);

	void reset(String votingId);

	void setStatus(String votingId, boolean open);

	List<Voting> listAll();

}
