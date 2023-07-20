package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.interfaces.Deal;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private Set<GameEngineCallback> gameEngineCallbacks;
	private List<Player> players;
	private CallbackHandler callbackHandler;
	// private Map<Player, Boolean> players ;
	private Deque<PlayingCard> deck;
	private Deal deal;

	public GameEngineImpl() {
		players = new ArrayList<Player>();
		deal = new DealImpl();
		// players = new HashMap<Player, Boolean>();
		deck = new LinkedList<PlayingCard>();
		gameEngineCallbacks = new HashSet<GameEngineCallback>();
		callbackHandler = new CallbackHandler(gameEngineCallbacks);
		for (PlayingCard.Suit s : PlayingCard.Suit.values()) {
			for (PlayingCard.Value v : PlayingCard.Value.values()) {
				deck.add(new PlayingCardImpl(s, v));
			}
		}
	}

	@Override
	public void dealPlayer(Player player, int delay) {
		if (player.getBet() != 0) {// 0 is no bet so don't deal any cards
			deal.deal(new PlayerDealStrategy(), this, callbackHandler, player, delay);
		}
	}

	@Override
	public void dealHouse(int delay) {
		deal.deal(new HouseDealStrategy(), this, callbackHandler, new HousePlayer(), delay);
	}

	@Override
	public void addPlayer(Player player) {
		boolean inPlayers = false;
		for (Player p : players) {// replace if playerid exists already
			if (p.getPlayerId() == player.getPlayerId()) {
				p = player;
				inPlayers = true;
			}
		}
		if (!inPlayers)
			players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		Player player = null;
		for (Player p : players) {
			if (p.getPlayerId() == id)
				player = p;
			break;
		}
		return player;
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return gameEngineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(players);
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		Collections.shuffle((LinkedList<PlayingCard>) deck);
		return deck;
	}

}
