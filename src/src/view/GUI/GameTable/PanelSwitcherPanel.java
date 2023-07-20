package view.GUI.GameTable;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.interfaces.Player;

//Panelswitcher is the container panel to switch panels between the different players
@SuppressWarnings("serial")
public class PanelSwitcherPanel extends JPanel {
	private List<PlayerPanel> playerPanels;
	private PlayerPanel selectedPanel;

	public List<PlayerPanel> getPlayerPanels() {
		return playerPanels;
	}

	public void setPlayerPanels(List<PlayerPanel> playerPanels) {
		this.playerPanels = playerPanels;
	}

	public PanelSwitcherPanel() {
		setLayout(new CardLayout());
		playerPanels = new ArrayList<PlayerPanel>();
	}

	public void switchPanel(Player player) {
		CardLayout c = (CardLayout) this.getLayout();
		c.show(this, player.getPlayerName());
		for (PlayerPanel pl : playerPanels) {
			if (pl.equals(player))
				selectedPanel = pl;
		}
	}

	public boolean panelExistsFor(Player player) {

		for (PlayerPanel pl : playerPanels) {
			if (pl.getName().equals(player.getPlayerName())) {
				return true;
			}
		}
		return false;
	}

	public void addPlayerPanel(Player player) {

		PlayerPanel p = new PlayerPanel(player.getPlayerName(), player);
		if (!panelExistsFor(player)) {
			playerPanels.add(p);
			this.add(p, player.getPlayerName());

			CardLayout c = (CardLayout) this.getLayout();
			c.show(this, player.getPlayerName());
			this.setSelectedPanel(p);
			this.validate();
		} else {
			switchPanel(player);
		}
	}

	public PlayerPanel getSelectedPanel() {
		return selectedPanel;
	}

	public void setScrollValue(int value) {
		this.getSelectedPanel().setScrollValue(value);
	}

	public PlayerPanel getSelectedPanel(Player player) {
		PlayerPanel panel = null;
		for (PlayerPanel pl : playerPanels) {
			if (pl.getName().equals(player.getPlayerName())) {
				panel = pl;
			}
		}
		return panel;
	}

	public void setSelectedPanel(PlayerPanel selectedPanel) {
		this.selectedPanel = selectedPanel;
	}

}
