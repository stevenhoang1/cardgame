package model.interfaces;

import model.CallbackHandler;

public interface Deal {
	void deal(DealStrategy dealStrategy, GameEngine gameEngine, CallbackHandler gameEngineCallback, Player player,
			int delay);

}
