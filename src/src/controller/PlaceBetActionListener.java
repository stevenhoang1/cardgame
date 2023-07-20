package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GUI.MainFrame;

public class PlaceBetActionListener implements ActionListener {
	private MainFrame frame;

	public PlaceBetActionListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (frame.placeBet())
			frame.deal();
	}

}
