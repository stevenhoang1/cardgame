package model;

import model.interfaces.DealStrategy;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class PlayerDealStrategy implements DealStrategy {

	@Override
	public void result(GameEngine gameEngine, CallbackHandler callbackHandler, Player player, int result) {
		player.setResult(result);
		callbackHandler.result(player, result, gameEngine);

	}

	@Override
	public void nextCard(GameEngine gameEngine, CallbackHandler callbackHandler, Player player, PlayingCard card) {
		callbackHandler.nextCard(player, card, gameEngine);

	}

	@Override
	public void bustCard(GameEngine gameEngine, CallbackHandler callbackHandler, Player player, PlayingCard card) {
		callbackHandler.bustCard(player, card, gameEngine);

	}

}
