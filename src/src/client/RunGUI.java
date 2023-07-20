package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.GUI.MainFrame;

public class RunGUI {
	public static void main(String args[]) {
		MainFrame mainFrame;
		final GameEngine gameEngine = new GameEngineImpl();

		mainFrame = new MainFrame(gameEngine);
		SwingUtilities.invokeLater(mainFrame);
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame));
		gameEngine.addPlayer(new SimplePlayer("1", "The Shark", 1000));
		gameEngine.addPlayer(new SimplePlayer("2", "Steven", 1000));
		//mainFrame.updatePlayersList();
		//mainFrame.updateUI();
	}
}
