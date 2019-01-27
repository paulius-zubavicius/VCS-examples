package com.owr.ex.spring.vote.model;

public class VoteOption {

	private String description;
	private int votes;

	public void vote() {
		votes++;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
