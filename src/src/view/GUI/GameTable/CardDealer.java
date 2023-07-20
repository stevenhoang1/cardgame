package view.GUI.GameTable;

import javax.swing.JPanel;

import model.RoundTracker;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.CardImageFactory;
import view.GUI.MainFrame;
//deals cards to the hand panels
public class CardDealer {
	private CardImageFactory cardImageFactory;

	public CardDealer() {
		cardImageFactory = new CardImageFactory();
	}

	private PanelSwitcherPanel getPanelSwitcher(MainFrame frame, Player player) {
		PanelSwitcherPanel switcherPanel;
		if (!player.getPlayerName().equals("House"))
			switcherPanel = frame.getPlayerPanelSwitcherPanel();
		else
			switcherPanel = frame.getHousePanelSwitcherPanel();
		return switcherPanel;
	}

	public void dealCard(MainFrame frame, Player player, PlayingCard card, RoundTracker roundTracker,
			boolean bustCard) {
		PanelSwitcherPanel switcherPanel = getPanelSwitcher(frame, player);

		PlayerPanel selectedPanel = switcherPanel.getSelectedPanel(player);

		if (!roundTracker.isPlayerPlaying(player)) {// checks on the first card delt whether the player is playing, if
													// not add a panel for the round
			selectedPanel.addHandPanel(roundTracker, false);
			roundTracker.startRound(player);
		}
		JPanel cardPanel = selectedPanel.getLastHandPanel().getCardViewPanel();
		CardView cardView = null;
		String cardText = "";
		if (bustCard)
			cardText = "BUST CARD";
		cardView = new CardView(cardText, cardImageFactory.getImage(card));

		cardPanel.add(cardView);

		frame.updateUI();

		selectedPanel.scrollToBottom();
	}

	public void finishRound(MainFrame frame, Player player, int result, RoundTracker roundTracker) {
		PanelSwitcherPanel switcherPanel = getPanelSwitcher(frame, player);
		HandPanel cardPanel = switcherPanel.getSelectedPanel(player).getLastHandPanel();
		cardPanel.finishRound(result);
		roundTracker.finishRound(player);
		frame.updateUI();
	}
}
