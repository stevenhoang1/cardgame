package view.GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameScrollAdjustmentListener;
import model.DealSequenceController;
import model.HousePlayer;
import model.RoundTracker;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GUI.GameTable.PanelSwitcherPanel;
import view.GUI.TopMenu.AddPlayerDialog;
import view.GUI.TopMenu.MenuBar;
import view.GUI.TopMenu.PlayerInfoToolBar;
import view.GUI.TopMenu.PlayerToolBar;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Runnable {
	/*
	 * Panel Definition MainFrame: parent frame that holds all the panels and allows
	 * access to all the children panels SwitcherPanels: Panel that allows for
	 * panels to switch between different players Player Panel: Panel belonging to
	 * each player HandPanel: Each handpanel contains the cards for the relevant
	 * round CardView: View for the image of the card itself
	 */
	private MenuBar menuBar;
	private PlayerToolBar playerToolBar;
	private GameEngine gameEngine;
	private PanelSwitcherPanel playerPanelSwitcherPanel;
	private PanelSwitcherPanel housePanelSwitcherPanel;

	private Player housePlayer;
	private JPanel toolBarPanel;
	private PlayerInfoToolBar playerInfoToolBar;
	private StatusBarPanel statusBarPanel;
	private RoundTracker roundTracker = new RoundTracker();
	private DealSequenceController dealSequenceController = new DealSequenceController(this);

	public PlayerInfoToolBar getDealToolBar() {
		return playerInfoToolBar;
	}

	public DealSequenceController getDealSequenceController() {
		return dealSequenceController;
	}

	private AddPlayerDialog addPlayerDialog;

	public PanelSwitcherPanel getPlayerPanelSwitcherPanel() {
		return playerPanelSwitcherPanel;
	}

	public PanelSwitcherPanel getHousePanelSwitcherPanel() {
		return housePanelSwitcherPanel;
	}

	public RoundTracker getRoundTracker() {
		return roundTracker;
	}

	public MainFrame(GameEngine gameEngine) {
		super("Assignment 2 BlackJack GUI");
		this.gameEngine = gameEngine;
	}

	public AddPlayerDialog getAddPlayerDialog() {
		return addPlayerDialog;
	}

	public void openAddPlayerDialog() {
		addPlayerDialog = new AddPlayerDialog(this);
	}

	public Player getHousePlayer() {
		return housePlayer;
	}

	public PlayerToolBar getPlayerToolBar() {
		return playerToolBar;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public StatusBarPanel getStatusBarPanel() {
		return statusBarPanel;
	}

	private void addToolBars() {
		menuBar = new MenuBar(this);
		playerToolBar = new PlayerToolBar(this);
		toolBarPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;

		toolBarPanel.add(playerToolBar, c);
		add(menuBar, BorderLayout.NORTH);
		add(toolBarPanel, BorderLayout.NORTH);
		playerToolBar.setFloatable(false);
		this.setJMenuBar(menuBar);
	}

	private void addGamePanels() {

		JPanel splitterPanel = new JPanel(new GridLayout(1, 2));
		add(splitterPanel, BorderLayout.CENTER);

		// PANEL FOR PLAYER
		playerPanelSwitcherPanel = new PanelSwitcherPanel();
		splitterPanel.add(playerPanelSwitcherPanel);

		// PANEL FOR HOUSE
		housePanelSwitcherPanel = new PanelSwitcherPanel();
		housePlayer = new HousePlayer();
		housePanelSwitcherPanel.addPlayerPanel(housePlayer);
		housePanelSwitcherPanel.getSelectedPanel().getScrollPane().getVerticalScrollBar()
				.addAdjustmentListener(new GameScrollAdjustmentListener(this));
		splitterPanel.add(housePanelSwitcherPanel);

	}

	@Override
	public void run() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		addToolBars();
		addGamePanels();
		statusBarPanel = new StatusBarPanel();
		add(statusBarPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
		updateUI();
		/*
		 * Game Engine seems to not add players right after MainFrame is initialized, so
		 * waiting on another thread to update the players list
		 */
		new Thread() {
			@Override
			public void run() {
				boolean run = true;
				while (run) {
					if (getGameEngine().getAllPlayers().size() != 0) {
						updatePlayersList();
						run = false;
					}
				}
			}
		}.start();
	}

//displays information about the players
	public void showInfoPanel() {
		if (playerInfoToolBar != null)
			return;
		playerInfoToolBar = new PlayerInfoToolBar(this);
		toolBarPanel.remove(playerToolBar);
		GridBagConstraints c = new GridBagConstraints();
		toolBarPanel.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.gridwidth = 1;
		c.gridheight = 1;
		toolBarPanel.add(playerToolBar, c);
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 10;
		c.gridheight = 1;
		toolBarPanel.add(playerInfoToolBar, c);
		updateUI();
	}

	public void addPlayerPanel(Player player) {
		playerPanelSwitcherPanel.addPlayerPanel(player);
	}

	public boolean placeBet() {
		boolean placeBet = false;
		try {
			int bet = Integer.parseInt(this.getPlayerToolBar().getBetAmount().getText());
			Player p = this.getPlayerPanelSwitcherPanel().getSelectedPanel().getPlayer();
			if (!p.placeBet(bet)) {
				throw new Exception(
						"Invalid bet, not enough points left or invalid number entered.\n Enter 0 and press place bet to skip turn");

			}
			showInfoPanel();
			getDealToolBar().updatePlayerInfo(p);
			updateUI();
			placeBet = true;
		} catch (NumberFormatException ne) {
			JOptionPane.showMessageDialog(this, "Error: Incorrect number entered", "Error encountered",
					JOptionPane.ERROR_MESSAGE);
			placeBet = false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error encountered",
					JOptionPane.ERROR_MESSAGE);
			placeBet = false;
		}
		return placeBet;
	}

//show the selected player's panel
	public void selectPlayer(Player p) {
		if (p != null) {
			addPlayerPanel(p);
			enableControls();
			getPlayerPanelSwitcherPanel().getSelectedPanel().getScrollPane().getVerticalScrollBar()
					.addAdjustmentListener(new GameScrollAdjustmentListener(this));

			if (roundTracker.getLastRound() > 0) {
				addMissingHandPanels(p);
			}
		}
		getPlayerToolBar().setBetAmount(p, p.getBet());
	}

	// add placeholder handpanels
	private void skipRound(int round, Player p) {
		getDealSequenceController().setReady(p);
		getRoundTracker().startRound(p);
		getPlayerPanelSwitcherPanel().getSelectedPanel(p).addHandPanel(round, true);
		getRoundTracker().finishRound(p);
	}

	public void deal() {
		Player p = getPlayerPanelSwitcherPanel().getSelectedPanel().getPlayer();
		// prevent the user from pressing deal twice before playing
		if (getRoundTracker().isPlayerPlaying(p) || getDealSequenceController().getReady(p)) {
			JOptionPane.showMessageDialog(this, "Error: Can't deal because player is already in play for this round",
					"Error encountered", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// skip the round and add missing hand panels
		if (p.getBet() == 0 && !getDealSequenceController().getReady(p)) {
			skipRound(getRoundTracker().getLastRound(), p);
		}
		// check if all players have played if true then deal house
		new Thread() {
			@Override
			public void run() {
				dealPlayer(p);
				getDealSequenceController().setReady(p);
				updateUI();
				if (getDealSequenceController().allPlayersReady()) {// start dealing house after all players have made a
																	// bet
					dealPlayer(getHousePlayer());
					getDealSequenceController().finishRound();
					updateUI();
				}
			}
		}.start();

	}

	private void dealPlayer(Player p) {
		disableControls();
		if (p.getPlayerName().equals("House"))
			getGameEngine().dealHouse(1000);
		else
			getGameEngine().dealPlayer(p, 1000);
		enableControls();
	}

	public void addPlayer(AddPlayerDialog addPlayerDialog) {
		try {
			String name = addPlayerDialog.getTextFieldName().getText();
			if (name.toLowerCase().equals("house"))
				throw new Exception("Invalid name");
			int initBet = Integer.parseInt(addPlayerDialog.getInitBet().getText());
			String ID = String.valueOf(getGameEngine().getAllPlayers().size() + 1);
			Player p = new SimplePlayer(ID, name, initBet);

			getGameEngine().addPlayer(p);

			updatePlayersList();
			addPlayerDialog.closeDialog();

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Error: Incorrect number entered", "Error encountered",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error encountered",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void addMissingHandPanels(Player p) {// add a "no play round" panel for each round the player missed on
		if (getPlayerPanelSwitcherPanel().getSelectedPanel(p).getHandPanels().size() == 0)
			for (int i = 0; i < roundTracker.getLastRound(); i++) {
				getPlayerPanelSwitcherPanel().getSelectedPanel(p).addHandPanel(i, true);
			}

	}

	public void disableControls() {
		this.playerToolBar.disableBet();
	}

	public void enableControls() {
		this.playerToolBar.enableBet();
		showInfoPanel();
		updateUI();
	}

	public void updateUI() {
		this.getStatusBarPanel().setPlayersNotReady(this.getDealSequenceController().waitingFor());

		if (this.getPlayerPanelSwitcherPanel().getSelectedPanel() != null) {
			Player player = this.getPlayerPanelSwitcherPanel().getSelectedPanel().getPlayer();
			this.getStatusBarPanel().setRoundLabel(roundTracker.getState(), player, roundTracker.getLastRound());
			this.getDealToolBar().updatePlayerInfo(player);
		}
		this.revalidate();
	}

	// fixes both scrollpanels to the same value
	public void setScrollValue(int value) {
		this.getHousePanelSwitcherPanel().setScrollValue(value);
		if (getPlayerPanelSwitcherPanel().getSelectedPanel() != null)
			this.getPlayerPanelSwitcherPanel().setScrollValue(value);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updatePlayersList() {
		JComboBox<String> box = playerToolBar.getPlayersComboBox();
		List<String> players = new ArrayList<String>();
		for (Player p : this.getGameEngine().getAllPlayers()) {
			players.add(p.getPlayerName());
			this.getDealSequenceController().addPlayer(p);
			this.roundTracker.trackPlayer(p);

		}
		box.setModel(new DefaultComboBoxModel(players.toArray()));
		updateUI();
	}

}
