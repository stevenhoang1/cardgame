package model;

public class Round {
	private int round;
	private boolean inProgress;

	public Round() {
		round = 0;
		inProgress = false;
	}

	public Round(int round) {
		this.round = round;
		inProgress = false;
	}

	public int getRound() {
		return round;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void startRound() {

		inProgress = true;
	}

	public void finishRound() {
		inProgress = false;
		round++;
	}
}
