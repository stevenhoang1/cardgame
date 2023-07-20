package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import model.interfaces.Player;
import view.GUI.MainFrame;

public class DealSequenceController {
	// Keeps track of which players are ready so we can deal house once all the players are ready
	private HashMap<Player, Boolean> playerReady;
	public DealSequenceController(MainFrame frame) {
		playerReady = new HashMap<Player, Boolean>();

	}

	public void addPlayer(Player player) {
		if (!playerReady.containsKey(player))
			playerReady.put(player, false);
	}

	public void setReady(Player player) {
		playerReady.put(player, true);
	}

	public boolean getReady(Player player) {
		return playerReady.get(player);
	}

	public void finishRound() {
		for (Player p : playerReady.keySet()) {
			playerReady.put(p, false);
		}
	}

	public Set<Player> waitingFor() {// list of players not ready
		Set<Player> players = new HashSet<Player>();
		for (Player p : playerReady.keySet()) {
			if (!playerReady.get(p)) {
				players.add(p);
			}
		}
		return players;
	}

	public boolean allPlayersReady() {
		for (Player p : playerReady.keySet()) {
			if (!playerReady.get(p)) {
				return false;
			}
		}
		return true;
	}
}
