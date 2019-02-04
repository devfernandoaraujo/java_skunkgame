package ca.bcit.comp1451.skunkinterface.fernandoaraujo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Common functions between buttons 
 * @author Fernando M Araujo
 * @since 2018-02-03
 * @version 1.0.0.0
 */
public class CustomSkunkGame extends SelectionAdapter {
	private Button btnNewGame;
	private Button btnNextRound;
	private Button btnRollDice;
	private Combo cmbQuantityOfDices;
	private Label lblTotalScore;
	private Label lblRoundScore;
	protected Shell shlSkunkGame;
	
	/**
	 * Main constructor of the class.
	 * @param btnNewGame button responsible to start a new game
	 * @param btnNextRound button responsible to change the round
	 * @param btnRollDice button responsible to roll a dices  
	 * @param cmbQuantityOfDices combo responsible to manage the quantity of dices
	 * @param lblTotalScore Label that shows the score of the game
	 * @param lblRoundScore Label that shows the score of the round
	 * @param shlSkunkGame swt interface 
	 */
	public CustomSkunkGame(Button btnNewGame, Button btnNextRound, Button btnRollDice, Combo cmbQuantityOfDices, Label lblTotalScore, Label lblRoundScore, Shell shlSkunkGame) {
		this.btnNewGame = btnNewGame;
		this.btnNextRound = btnNextRound;
		this.btnRollDice = btnRollDice;
		this.cmbQuantityOfDices = cmbQuantityOfDices;
		this.lblTotalScore = lblTotalScore;
		this.lblRoundScore = lblRoundScore;
		this.shlSkunkGame = shlSkunkGame;
	}
	
	public void disableQuantityOfDicesSeletion(boolean enabled) {
		
		if(enabled != cmbQuantityOfDices.getEnabled()) {
			cmbQuantityOfDices.setEnabled(enabled);
		}
	}
	
	public void endGame(int gameScore) {
		//Create message of end of game
		MessageBox message = new MessageBox(shlSkunkGame,SWT.ICON_INFORMATION|SWT.OK);
		
		message.setText("End of the game");
		message.setMessage("It is the end of the game.\nYour score is " + gameScore + ".");
		message.open();
		
		
		btnNextRound.setEnabled(false);
		btnRollDice.setEnabled(false);
		disableQuantityOfDicesSeletion(true);
	}
	
}
