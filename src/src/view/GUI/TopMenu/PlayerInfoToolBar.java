package view.GUI.TopMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;
import view.GUI.MainFrame;

@SuppressWarnings("serial")
public class PlayerInfoToolBar extends JPanel {
	private JLabel playerBalance;
	private JLabel playerName;
	private JLabel playerBet;

	public void setPlayerBalance(Player player, int balance) {
		playerBalance.setText(String.format("%s has %d points", player.getPlayerName(), balance));
	}

	public void updatePlayerInfo(Player player) {
		playerName.setText(String.format("%s(%s) Summary", player.getPlayerName(), player.getPlayerId()));
		playerBalance.setText(String.format("%d points left", player.getPoints()));
		playerBet.setText(String.format("%d points betted on this round", player.getBet()));
	}

	public PlayerInfoToolBar(MainFrame frame) {
		super(new BorderLayout());
		
		JPanel infoPanel = new JPanel(new GridLayout(5, 1));
		playerBet = new JLabel();
		playerBalance = new JLabel();
		playerName = new JLabel();
		
		infoPanel.add(new JLabel());
		infoPanel.add(playerName);
		infoPanel.add(playerBalance);
		infoPanel.add(playerBet);
		add(infoPanel, BorderLayout.WEST);
		
		infoPanel.setOpaque(false);
		setBackground(new Color(255, 145, 145));
	}


}
