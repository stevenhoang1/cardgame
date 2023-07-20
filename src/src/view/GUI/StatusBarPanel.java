package view.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.RoundTracker.GameState;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel {
	private JLabel roundLabel = new JLabel("Waiting on round 0", JLabel.RIGHT);
	private JLabel playersNotReady = new JLabel("", JLabel.LEFT);

	public StatusBarPanel() {
		setLayout(new GridLayout(1, 2));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		playersNotReady.setBorder(blackBorder);
		playersNotReady.setFont(new Font(playersNotReady.getFont().getFontName(), Font.BOLD, 13));
		roundLabel.setBorder(blackBorder);
		roundLabel.setFont(new Font(playersNotReady.getFont().getFontName(), Font.BOLD, 14));
		this.setBackground(new Color(145, 117, 95));

		add(playersNotReady);
		add(roundLabel);
	}

	public void setPlayersNotReady(Set<Player> players) {
		StringBuilder sb = new StringBuilder();
		sb.append("Place a bet for ");
		boolean delete = false;
		for (Player p : players) {
			sb.append(p.getPlayerName());
			sb.append(", ");
			delete = true;
		}
		if (delete)//deletes the last comma(for extra polish)
			sb.deleteCharAt(sb.length() - 2);
		sb.append("to continue");
		playersNotReady.setText(sb.toString());
	}

	public void setRoundLabel(GameState state, Player p, int round) {
		StringBuilder sb = new StringBuilder();
		switch (state) {
		case waiting:
			sb.append("Waiting for players");
			break;
		case dealingPlayer:
			sb.append("Dealing to " + p.getPlayerName());
			break;
		case dealingHouse:
			sb.append("Dealing to House");
			break;
		}

		sb.append(" on round ");
		sb.append(round);
		this.roundLabel.setText(sb.toString());
	}
}
