package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	private Suit suit;
	private Value value;
	private int score;

	public PlayingCardImpl(Suit s, Value v) {
		this.suit = s;
		this.value = v;
		switch (value) {
		case ACE:
			score = 1;
			break;
		case TWO:
			score = 2;
			break;
		case THREE:
			score = 3;
			break;
		case FOUR:
			score = 4;
			break;
		case FIVE:
			score = 5;
			break;
		case SIX:
			score = 6;
			break;
		case SEVEN:
			score = 7;
			break;
		case EIGHT:
			score = 8;
			break;
		case NINE:
			score = 9;
			break;
		case JACK:
		case QUEEN:
		case KING:
		case TEN:
			score = 10;
			break;
		}
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(PlayingCard card) {
		if (getSuit() == card.getSuit() && getValue() == card.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d", getSuit(), getValue(), getScore());
	}

}
