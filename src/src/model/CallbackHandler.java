package model;

import java.util.Set;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class CallbackHandler {
	// calls every gameEngineCallbacks
	public CallbackHandler(Set<GameEngineCallback> gameEngineCallbacks) {
		this.gameEngineCallbacks = gameEngineCallbacks;
	}

	private Set<GameEngineCallback> gameEngineCallbacks;

	public void houseResult(int result, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.houseResult(result, gameEngine);
		}
	}

	public void nextHouseCard(PlayingCard card, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.nextHouseCard(card, gameEngine);
		}

	}

	public void houseBustCard(PlayingCard card, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.houseBustCard(card, gameEngine);
		}
	}

	public void bustCard(Player player, PlayingCard card, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.bustCard(player, card, gameEngine);
		}
	}

	public void nextCard(Player player, PlayingCard card, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.nextCard(player, card, gameEngine);
		}
	}

	public void result(Player player, int result, GameEngine gameEngine) {
		for (GameEngineCallback ge : gameEngineCallbacks) {
			ge.result(player, result, gameEngine);
		}
	}

}
