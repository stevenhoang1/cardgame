package model;

import model.interfaces.DealStrategy;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class HouseDealStrategy implements DealStrategy {

	@Override
	public void result(GameEngine gameEngine, CallbackHandler callbackHandler, Player housePlayer, int result) {
		housePlayer.setResult(result);
		for (Player p : gameEngine.getAllPlayers()) {
			if (p.getResult() == housePlayer.getResult()) {
				// nothing happens
			} else if (playerWins(p, housePlayer)) // player wins
				addPoints(p, p.getBet());
			else // house wins
				addPoints(p, -p.getBet());
			p.resetBet();
		}

		callbackHandler.houseResult(result, gameEngine);
	}

	private boolean playerWins(Player player, Player house) {
		return player.getResult() > house.getResult();
	}

	private void addPoints(Player player, int points) {
		player.setPoints(player.getPoints() + points);
	}

	@Override
	public void nextCard(GameEngine gameEngine, CallbackHandler callbackHandler, Player player, PlayingCard card) {
		callbackHandler.nextHouseCard(card, gameEngine);

	}

	@Override
	public void bustCard(GameEngine gameEngine, CallbackHandler callbackHandler, Player player, PlayingCard card) {
		callbackHandler.houseBustCard(card, gameEngine);

	}
}
