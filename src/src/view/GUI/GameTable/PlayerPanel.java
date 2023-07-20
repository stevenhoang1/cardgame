package view.GUI.GameTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.RoundTracker;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private List<HandPanel> handPanels;// Array to store all the hands previously played
	private JPanel cardPanel;// holds the hand and grid size increases with each hand
	private Player player;
	private String name;//used to switch panels
	JScrollPane scrollPane;

	public PlayerPanel(String name, Player player) {
		this.name = name;
		this.player = player;

		handPanels = new ArrayList<HandPanel>();
		this.setLayout(new BorderLayout());
		JLabel playerName = new JLabel(player.getPlayerName());
		cardPanel = new JPanel();
		scrollPane = new JScrollPane(cardPanel);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setBackground(new Color(145, 117, 95));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setFont(new Font(playerName.getFont().getFontName(), Font.PLAIN, 20));
		cardPanel.setLayout(new GridLayout(1, 1));

		add(playerName, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	public HandPanel getLastHandPanel() {
		return handPanels.get(handPanels.size() - 1);
	}

	public void setScrollValue(int value) {
		scrollPane.getVerticalScrollBar().setValue(value);
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public List<HandPanel> getHandPanels() {
		return handPanels;
	}

	public void scrollToBottom() {
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}

	public void addHandPanel(RoundTracker roundTracker, boolean noPlay) {

		addHandPanel(roundTracker.getRound(player), noPlay);
	}

	public void addHandPanel(int round, boolean noPlay) {

		cardPanel.setLayout(new GridLayout(round + 1, 1));
		HandPanel hp = new HandPanel(round);
		handPanels.add(hp);
		this.cardPanel.add(hp);
		if (noPlay)
			hp.add(new JLabel("No play round", JLabel.CENTER));
	}

	public boolean equals(PlayerPanel p) {
		if (p.getPlayer().getPlayerId().equals(this.player.getPlayerId())) {
			return true;
		}
		return false;
	}

	public boolean equals(Player p) {
		if (p.getPlayerId().equals(this.player.getPlayerId())) {
			return true;
		}
		return false;
	}
}
