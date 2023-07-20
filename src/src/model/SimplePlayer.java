package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerName;
	private int points;
	private String playerId;
	private int result;
	private int bet;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;

	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean placeBet(int bet) {
		if (bet > points || bet < 0) {
			resetBet();
			return false;
		} else
			this.bet = bet;
		return true;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {

		this.result = result;

	}

	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, points=%d", this.getPlayerId(), this.getPlayerName(),
				this.getPoints());
	}
}
