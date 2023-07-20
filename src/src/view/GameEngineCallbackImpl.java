package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl() {
		// FINE shows dealing output, INFO only shows result
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {

		// intermediate results logged at Level.FINE
		logger.log(Level.INFO, String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString()));

	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%s, final result=%d", player.getPlayerName(), result));

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		logger.log(Level.INFO,
				String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card.toString()));

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.INFO, String.format("Card Dealt to House .. %s", card.toString()));

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.INFO, String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString()));

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		logger.log(Level.INFO, String.format("House, final result=%d", result));
		StringBuilder sb = new StringBuilder("Final Player Result");
		for (Player p : engine.getAllPlayers()) {
			sb.append('\n');
			if (p != null)
				sb.append(p.toString());
		}
		logger.log(Level.INFO, sb.toString());

	}

}
