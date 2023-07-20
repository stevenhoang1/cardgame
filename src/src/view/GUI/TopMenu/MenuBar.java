package view.GUI.TopMenu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.OpenAddPlayerDialogActionListener;
import view.GUI.MainFrame;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu menu;
	private JMenuItem menuItem;

	public MenuBar(MainFrame mainFrame) {
		menu = new JMenu("Menu");
		add(menu);
		menuItem = new JMenuItem("Add Player");
		menu.add(menuItem);
		menuItem.addActionListener(new OpenAddPlayerDialogActionListener(mainFrame));
	}
}
