package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.Player;
import view.GUI.MainFrame;

public class PlayerChangeActionListener implements ActionListener {
	private MainFrame frame;

	public PlayerChangeActionListener(MainFrame frame) {
		this.frame = frame;
	}

	// check if playerpanel exists then add new panel or switch to the panel
	@Override
	public void actionPerformed(ActionEvent arg0) {

		JComboBox<?> playerListGui = (JComboBox<?>) arg0.getSource();
		// find player by their name to id
		Player p = null;
		for (Player pp : frame.getGameEngine().getAllPlayers()) {
			if (playerListGui.getSelectedItem().toString().equals(pp.getPlayerName())) {
				p = pp;
			}
		}
		frame.selectPlayer(p);
	}

}
