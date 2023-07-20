package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GUI.MainFrame;

public class OpenAddPlayerDialogActionListener implements ActionListener {
	private MainFrame mainFrame;

	public OpenAddPlayerDialogActionListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// open up dialog box System.out.println(e);
		mainFrame.openAddPlayerDialog();
	}

}
