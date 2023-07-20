package view.GUI.GameTable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
//picture of the card
@SuppressWarnings("serial")
public class CardView extends JLabel {
	public CardView(String cardText, ImageIcon icon) {
		super(cardText, icon, JLabel.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);

	}

}
