package model;

import model.interfaces.Player;

public class HousePlayer implements Player {
	private String playerName;
	private int points;
	private int result;

	public HousePlayer() {
		this.playerName = "House";
		this.points = 0;
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
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;

	}

	// bottom methods are dummy methods
	@Override
	public String getPlayerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placeBet(int bet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetBet() {

	}

}
