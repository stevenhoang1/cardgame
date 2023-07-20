package view.GUI.TopMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.PlaceBetActionListener;
import controller.PlayerChangeActionListener;
import model.interfaces.Player;
import view.GUI.MainFrame;

@SuppressWarnings("serial")
public class PlayerToolBar extends JToolBar {
	private JComboBox<String> playersComboBox;
	private JTextField betAmount;
	private JButton btnBet;

	public PlayerToolBar(MainFrame frame) {
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		playersComboBox = new JComboBox<String>();
		betAmount = new JTextField(20);
		btnBet = new JButton("Place Bet");

		leftPanel.add(new JLabel("Select player:"));
		leftPanel.add(playersComboBox);
		rightPanel.add(new JLabel("Enter amount to bet:"));
		rightPanel.add(betAmount);
		rightPanel.add(btnBet);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		this.setBackground(new Color(145, 117, 95));
		leftPanel.setOpaque(false);
		rightPanel.setOpaque(false);
		btnBet.setEnabled(false);
		betAmount.setEnabled(false);
		btnBet.addActionListener(new PlaceBetActionListener(frame));
		playersComboBox.addActionListener(new PlayerChangeActionListener(frame));
	}

	public JTextField getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Player p, int betAmount) {
		String betAmountString = String.valueOf(p.getBet());
		getBetAmount().setText(betAmountString);
	}

	public void enableBet() {
		btnBet.setEnabled(true);
		betAmount.setEnabled(true);
	}

	public void disableBet() {
		btnBet.setEnabled(false);
		betAmount.setEnabled(false);
	}

	public JComboBox<String> getPlayersComboBox() {
		return playersComboBox;
	}

}
