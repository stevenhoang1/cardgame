package view;

import model.HousePlayer;
import model.RoundTracker;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.GUI.MainFrame;
import view.GUI.GameTable.CardDealer;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainFrame frame;
	private RoundTracker roundTracker;
	private CardDealer cardDealer;

	public GameEngineCallbackGUI(MainFrame frame) {
		this.frame = frame;
		roundTracker = frame.getRoundTracker();
		cardDealer = new CardDealer();
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		cardDealer.dealCard(frame, player, card, roundTracker, false);
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		cardDealer.dealCard(frame, player, card, roundTracker, true);
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		cardDealer.finishRound(frame, player, result, roundTracker);
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		HousePlayer house = (HousePlayer) frame.getHousePlayer();
		cardDealer.dealCard(frame, house, card, roundTracker, false);
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		HousePlayer house = (HousePlayer) frame.getHousePlayer();
		cardDealer.dealCard(frame, house, card, roundTracker, true);

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		HousePlayer house = (HousePlayer) frame.getHousePlayer();
		cardDealer.finishRound(frame, house, result, roundTracker);

	}

}
