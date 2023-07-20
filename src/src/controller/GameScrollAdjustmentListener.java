package controller;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import view.GUI.MainFrame;

public class GameScrollAdjustmentListener implements AdjustmentListener {
	private MainFrame frame;

	public GameScrollAdjustmentListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		frame.setScrollValue(e.getValue());
	}

}
