package model;

import java.util.HashMap;

import model.interfaces.Player;

//keeps track of which round the players and the game is up to
public class RoundTracker {
	private HashMap<Player, Round> playersInPlay;

	public RoundTracker() {
		playersInPlay = new HashMap<Player, Round>();
		state = GameState.waiting;
	}

	private int lastRound = 0;// last round for the all the players

	public int getLastRound() {
		return lastRound;
	}

	private GameState state;

	public enum GameState {
		waiting, dealingPlayer, dealingHouse;
	}

	public void trackPlayer(Player player) {
		if (!playersInPlay.containsKey(player))
			playersInPlay.put(player, new Round(lastRound));
	}

	public GameState getState() {
		return state;
	}

	public boolean isPlayerPlaying(Player player) {
		if (!playersInPlay.containsKey(player)) {
			trackPlayer(player);
			return false;
		}
		return playersInPlay.get(player).isInProgress();
	}

	public void startRound(Player player) {
		playersInPlay.get(player).startRound();
		if (player.getPlayerName().equals("House"))
			state = GameState.dealingHouse;
		else
			state = GameState.dealingPlayer;
	}

	public void finishRound(Player player) {
		playersInPlay.get(player).finishRound();
		if (player.getPlayerName().equals("House")) {
			lastRound++;
		}
		state = GameState.waiting;
	}

	public int getRound(Player player) {
		return playersInPlay.get(player).getRound();
	}

}
