package com.owr.ex.spring.vote.model;

import java.util.ArrayList;
import java.util.List;

public class Voting {

	private String votingId;
	private String question;
	private boolean active = true;
	private List<VoteOption> options = new ArrayList<>();
	private List<String> voters = new ArrayList<>();

	public String getVotingId() {
		return votingId;
	}

	public void setVotingId(String votingId) {
		this.votingId = votingId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<VoteOption> getOptions() {
		return options;
	}

	public void setOptions(List<VoteOption> options) {
		this.options = options;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean open) {
		this.active = open;
	}

	public List<String> getVoters() {
		return voters;
	}

	public void setVoters(List<String> voters) {
		this.voters = voters;
	}

}
