package view.GUI.GameTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


//hand panels shows which rounds and holds the panel for the cards
//each hand panel indicates one round of play
@SuppressWarnings("serial")
public class HandPanel extends JPanel {
	private JPanel cardViewPanel;
	private JLabel roundIndicator;

	public JPanel getCardViewPanel() {
		return cardViewPanel;
	}

	public HandPanel(int round) {
		JPanel roundIndicatorPanel = new JPanel();
		roundIndicator = new JLabel("Round " + round);
		cardViewPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		roundIndicatorPanel.add(roundIndicator);
		this.add(roundIndicatorPanel, BorderLayout.NORTH);
		this.add(cardViewPanel, BorderLayout.CENTER);
		
		this.setBackground(new Color(143, 239, 127));
		cardViewPanel.setLayout(new FlowLayout());
		cardViewPanel.setBackground(new Color(101, 242, 77));
		this.revalidate();
	}
	
	public void finishRound(int score) {
		roundIndicator.setText(roundIndicator.getText() + " result is " + score);
	}
}
