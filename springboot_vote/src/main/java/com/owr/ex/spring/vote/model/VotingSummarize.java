package com.owr.ex.spring.vote.model;

public class VotingSummarize {

	private String votingId;
	private String question;

	public VotingSummarize(Voting voting) {
		votingId = voting.getVotingId();
		question = voting.getQuestion();
	}

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

}
