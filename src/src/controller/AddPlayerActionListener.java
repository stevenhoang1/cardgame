package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GUI.MainFrame;
import view.GUI.TopMenu.AddPlayerDialog;

public class AddPlayerActionListener implements ActionListener {
	private MainFrame frame;
	private AddPlayerDialog addPlayerDialog;

	public AddPlayerActionListener(MainFrame frame, AddPlayerDialog addPlayerDialog) {
		this.frame = frame;
		this.addPlayerDialog = addPlayerDialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		frame.addPlayer(addPlayerDialog);
	}

}
