package ca.bcit.comp1451.skunkgame.fernandoaraujo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Skunk game functionalities
 * @author Fernando M Araujo
 * @since 2019-01-16
 * @version 1.0.0.0
 */
public class Skunk {
	
	private int roundScore;
	private int gameScore;
	private int quantityOfDices;
	private SkunkRound round;
	private int roundQuantityOfRolls;
	private int rollPoints;
	private final int MAXIMUM_ROLL_POINTS = 100;
	
	/**
	 * Return the round score
	 * @return Integer value with the round Score
	 */
	public int getRoundScore() {
		return this.roundScore;
	}
	
	private void startRoundScore() {
		this.roundScore= 0;
	}
	
	private void startGameScore() {
		this.gameScore = 0;
	}
	
	private void startRoundQuantityOfRolls() {
		this.roundQuantityOfRolls = 0;
	}
	
	/**
	 * Get the quantity of rolls in the round
	 * @return Integer value with the quantity of rolls on the round
	 */
	public int getRoundQuantityOfRolls() {
		return this.roundQuantityOfRolls;
	}
	
	private void incrementRoundScore(int points) {
		this.roundScore += points;
	}
	
	private void incrementRoundQuantityOfRolls() {
		this.roundQuantityOfRolls +=1;
	}
	
	private void incrementGameScore(int points) {
		this.gameScore += points;
	}
	
	private void decrementGameScore(int points) {
		this.gameScore -= points;
	}
	
	public int getGameScore() {
		return this.gameScore;
	}
	
	/**
	 * Get the quantity of dices on play 
	 * @return Integer value with the quantity of dices in the play 
	 */
	public int getQuantityOfDices() {
		return this.quantityOfDices;
	}
	
	/**
	 * Set the quantity of dices in play
	 * @param quantityOfDices Integer value with the quantity of dices
	 */
	public void setQuantityOfDices(int quantityOfDices) {
		this.quantityOfDices = quantityOfDices;
	}
	
	private void setRollPoints(int points) {
		this.rollPoints = points;
	}
	
	/**
	 * Get the value of the roll's points 
	 * @return Integer value with the roll's points
	 */
	public int getRollPoints() {
		return this.rollPoints;
	}
	
	/**
	 * Get the actual game's round
	 * @return SkunkRound object 
	 */
	public SkunkRound getRound() {
		return this.round;
	}
	
	private int getDice() {
		int dice = new Random().nextInt(6) + 1;
		
		return dice;
	}
	
	/**
	 * Change the game's round
	 */
	public void nextRound() {
		switch(this.round) {
			case First:
				this.round = SkunkRound.Second;
				break;
			case Second:
				this.round = SkunkRound.Third;
				break;
			case Third:
				this.round = SkunkRound.Fourth;
				break;
			case Fourth:
				this.round = SkunkRound.Fifth;
				break;
			case Fifth: //Do nothing it is the end of the game
				this.round = SkunkRound.End;
				break;
		}
		
		//Re start round
		this.startRoundScore();
		this.startRoundQuantityOfRolls();
	}
	
	private boolean hasOnesFace(ArrayList<Integer> dices) {
		
		Iterator<Integer> iteratorDice = dices.iterator();
		
		int dice = 0;
		int quantityOfOnes = 0;
		while(iteratorDice.hasNext()) {
			dice = iteratorDice.next();
			
			if(dice == 1)
			{
				quantityOfOnes++;
			}
		}
		
		if(quantityOfOnes > 0) {
			this.setRollPoints(0);
			this.decrementGameScore(this.getRoundScore());
			this.startRoundScore();
		}
		
		if(quantityOfOnes > 1)
		{
			this.startGameScore();
		}
		
		return quantityOfOnes > 0;
	}
	
	private void computeRollPoints(ArrayList<Integer> dices) {
		
		
		if(this.hasOnesFace(dices))
		{
			this.incrementRoundQuantityOfRolls();
			return;
		}
		
		Iterator<Integer> iteratorDice = dices.iterator();
		
		int previewDice = 0;
		HashMap<Integer, Boolean> diceEquality = new HashMap<Integer,Boolean>(); 
		int points = 0;
		int roundDice;
		int mapKey = 1;
		
		while(iteratorDice.hasNext()) {
			roundDice = iteratorDice.next();
			//If previewDice == 0 mean first dice
			if(roundDice == previewDice || previewDice == 0) {
				diceEquality.put(mapKey,true);
			}
			else {
				diceEquality.put(mapKey,false);
			}
			
			points += roundDice;
			previewDice = roundDice;
			mapKey++;
		}
		
		if(this.getQuantityOfDices() == 3 && (diceEquality.get(1) == diceEquality.get(2) && diceEquality.get(2) == diceEquality.get(3))) {
			points= this.MAXIMUM_ROLL_POINTS;
		}
		
		this.setRollPoints(points);
		
		this.incrementGameScore(points);
		this.incrementRoundScore(points);
		this.incrementRoundQuantityOfRolls();
		
	}
	
	/**
	 * Get the dices numbers rolled  
	 * @return Array with dices' numbers  
	 */
	public ArrayList<Integer> rollDice() {
		ArrayList<Integer> roundRoll = new ArrayList<>();
		
		int quantityOfRoll = 1;
		int diceFace;
		
		do {
			
			diceFace = this.getDice();
			
			//It is the first roll of the round it must prevent 1's 
			if(diceFace == 1 && this.getRoundQuantityOfRolls() == 0)
			{
				do {
					diceFace = this.getDice();
					
				}while(diceFace <= 1);
			}
			
			roundRoll.add(diceFace);
			
			quantityOfRoll++;
			
		}while(quantityOfRoll <= this.getQuantityOfDices());
		
		this.computeRollPoints(roundRoll);
		
		return roundRoll;
	}
	
	private Skunk(SkunkBuilder builder) {
		this.quantityOfDices=builder.quantityOfDices;
		this.gameScore = builder.gameScore;
		this.roundScore = builder.roundScore;
		this.round = builder.round;
		this.rollPoints = builder.rollPoints;
		
		
	}
	
	/**
	 * Nested class used as builder
	 */
	public static class SkunkBuilder{
		
		private int quantityOfDices;
		private int roundScore;
		private int gameScore;
		private SkunkRound round;
		private int rollPoints;
		
		/**
		 * Main constructor
		 * @param quantityOfDices Integer value with the quantity of dices in play
		 */
		public SkunkBuilder(int quantityOfDices) {
			this.setQuantityOfDices(quantityOfDices);
			this.setGameScore(0);
			this.setRoundScore(0);
			this.setRound(SkunkRound.First);
			this.setRollPoints(0);
		}
		
		private void setQuantityOfDices(int quantityOfDices) {
			this.quantityOfDices = quantityOfDices;
			
		}
		
		private void setRoundScore(int roundScore) {
			this.roundScore = roundScore;
		}

		private void setGameScore(int gameScore) {
			this.gameScore = gameScore;
		}

		private void setRound(SkunkRound round) {
			this.round = round;
		}
		
		private void setRollPoints(int points) {
			this.rollPoints = points;
		}

		/**
		 * Builder constructor
		 * @return Skunk object
		 */
		public Skunk builder() {
			return new Skunk(this);
		}
		
	}

}
