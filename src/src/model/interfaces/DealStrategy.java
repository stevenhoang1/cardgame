package model.interfaces;

import model.CallbackHandler;

public interface DealStrategy {
	void result(GameEngine gameEngine, CallbackHandler gameEngineCallback, Player player, int result);

	void nextCard(GameEngine gameEngine, CallbackHandler gameEngineCallback, Player player, PlayingCard card);

	void bustCard(GameEngine gameEngine, CallbackHandler gameEngineCallback, Player player, PlayingCard card);
}
