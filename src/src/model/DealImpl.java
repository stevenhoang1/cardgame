package model;

import model.interfaces.Deal;
import model.interfaces.DealStrategy;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class DealImpl implements Deal {

	private static final int MAX_PLAYS = 5;

	private void finishRound(DealStrategy dealStrategy, GameEngine gameEngine, CallbackHandler callbackHandler,
			Player player, PlayingCard card, int currentHandScore, boolean isBust) {
		if (isBust)// dont wanna call the bustcard if it is equal or less than 21
			dealStrategy.bustCard(gameEngine, callbackHandler, player, card);
		else
			dealStrategy.nextCard(gameEngine, callbackHandler, player, card);
		dealStrategy.result(gameEngine, callbackHandler, player, currentHandScore);
	}

	public void deal(DealStrategy dealStrategy, GameEngine gameEngine, CallbackHandler callbackHandler, Player player,
			int delay) {
		int currentHandScore = 0;
		for (int i = 0; i < MAX_PLAYS; i++) {

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {

			}
			PlayingCard card = gameEngine.getShuffledDeck().getLast();

			currentHandScore += card.getScore();
			if ((i + 1 == (MAX_PLAYS) && !isBust(currentHandScore)) || isBlackJack(currentHandScore)) {
				// last iteration and still hasnt busted yet or 21
				finishRound(dealStrategy, gameEngine, callbackHandler, player, card, currentHandScore,
						isBust(currentHandScore));
				return;

			} else if (isBust(currentHandScore)) {// more than 21
				finishRound(dealStrategy, gameEngine, callbackHandler, player, card, currentHandScore - card.getScore(),
						isBust(currentHandScore));
				return;
			} else // less than 21
				dealStrategy.nextCard(gameEngine, callbackHandler, player, card);
		}
	}

	private boolean isBust(int currentHandScore) {
		return currentHandScore > GameEngine.BUST_LEVEL;
	}

	private boolean isBlackJack(int currentHandScore) {
		return currentHandScore == GameEngine.BUST_LEVEL;
	}

}
