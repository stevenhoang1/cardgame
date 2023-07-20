package view;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

import model.PlayingCardImpl;
import model.interfaces.PlayingCard;

public class CardImageFactory {
	private HashMap<PlayingCard, ImageIcon> deckImages;

	public HashMap<PlayingCard, ImageIcon> getDeckImages() {
		return deckImages;
	}

//
	public ImageIcon getImage(PlayingCard card) {
		ImageIcon scaledImg = null;
		for (PlayingCard c : deckImages.keySet()) {
			if (c.equals(card)) {
				Image img = deckImages.get(c).getImage();
				// palyer
				scaledImg = new ImageIcon(
						img.getScaledInstance(img.getWidth(null) / 11, img.getHeight(null) / 11, Image.SCALE_SMOOTH));
			}
		}
		return scaledImg;
	}

	public CardImageFactory() {
		deckImages = new HashMap<PlayingCard, ImageIcon>();
		// build a new reference deck for images

		for (PlayingCard.Suit s : PlayingCard.Suit.values()) {
			for (PlayingCard.Value v : PlayingCard.Value.values()) {
				PlayingCard card = new PlayingCardImpl(s, v);
				StringBuilder sb = new StringBuilder("images" + File.separator + "cards" + File.separator);

				// build image string
				switch (v) {
				case ACE:
					sb.append("A");
					break;
				case JACK:
					sb.append("J");
					break;
				case QUEEN:
					sb.append("Q");
					break;
				case KING:
					sb.append("K");
					break;
				default:
					sb.append(card.getScore());
					break;
				}
				sb.append(s.toString().charAt(0));
				sb.append(".png");
				// add into a hashmap for reference
				deckImages.put(card, new ImageIcon(sb.toString()));
			}
		}
	}

}
