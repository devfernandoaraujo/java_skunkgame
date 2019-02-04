package ca.bcit.comp1451.skunkinterface.fernandoaraujo;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import ca.bcit.comp1451.skunkgame.fernandoaraujo.Skunk;
import ca.bcit.comp1451.skunkgame.fernandoaraujo.SkunkRound;

public class SkunkGame {

	protected Shell shlSkunkGame;
	private Table table;
	private Skunk skunk;
	private CustomSkunkGame customSkunkGame;
	private int quantityOfDices;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SkunkGame window = new SkunkGame();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		createGameInstance();
		shlSkunkGame.open();
		shlSkunkGame.layout();
		while (!shlSkunkGame.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	
	/**
	 * Create the instance of the game.
	 */
	protected void createGameInstance() {
		
		skunk = new Skunk.SkunkBuilder(quantityOfDices).builder();
	}
	
	//Return the position of a dice
	protected int getDicePosition(double groupWidth, double diceWidth, int quantityOfDices, int dicePosition) {
		
		int freeSpace = (int)(groupWidth -(diceWidth * quantityOfDices));
		
		
		int margin = freeSpace/(quantityOfDices +1);
		
		
		
		int position = (int)(margin + ((dicePosition -1)* diceWidth));
		
		
		return position;
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSkunkGame = new Shell(shlSkunkGame,SWT.TITLE|SWT.CLOSE);
		shlSkunkGame.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.BOLD | SWT.ITALIC));
		shlSkunkGame.setImage(SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/skunk.jpg"));
		shlSkunkGame.setSize(1041, 670);
		shlSkunkGame.setText("SKUNK GAME");
		
		
				
		Composite composite = new Composite(shlSkunkGame, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		composite.setBounds(10, 10, 995, 191);
		
		Label lblNewLabel_1 = new Label(composite, SWT.BORDER);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBounds(189, 0, 617, 191);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/skunk_template.png"));
		
		Label label = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(419, 207, 2, 404);
		
		table = new Table(shlSkunkGame, SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		table.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		table.setHeaderVisible(true);
		table.setBounds(427, 226, 578, 322);
		table.setLinesVisible(true);
		
		TableColumn tblGameColumn1 = new TableColumn(table, SWT.CENTER);
		tblGameColumn1.setResizable(false);
		tblGameColumn1.setWidth(110);
		tblGameColumn1.setText("S");
		
		TableColumn tblGameColumn2 = new TableColumn(table, SWT.CENTER);
		tblGameColumn2.setResizable(false);
		tblGameColumn2.setWidth(110);
		tblGameColumn2.setText("K");
		
		TableColumn tblGameColumn3 = new TableColumn(table, SWT.CENTER);
		tblGameColumn3.setResizable(false);
		tblGameColumn3.setWidth(110);
		tblGameColumn3.setText("U");
		
		TableColumn tblGameColumn4 = new TableColumn(table, SWT.CENTER);
		tblGameColumn4.setResizable(false);
		tblGameColumn4.setWidth(110);
		tblGameColumn4.setText("N");
		
		TableColumn tblGameColumn5 = new TableColumn(table, SWT.CENTER);
		tblGameColumn5.setResizable(false);
		tblGameColumn5.setWidth(110);
		tblGameColumn5.setText("K");
		
		Label label_1 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(419, 554, 592, 2);
		
		Label lblTotalScoreInfo = new Label(shlSkunkGame, SWT.NONE);
		lblTotalScoreInfo.setBounds(446, 580, 90, 20);
		lblTotalScoreInfo.setText("Total Score:");
		
		Label lblTotalScore = new Label(shlSkunkGame, SWT.NONE);
		lblTotalScore.setAlignment(SWT.CENTER);
		lblTotalScore.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		lblTotalScore.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		lblTotalScore.setBounds(538, 580, 116, 20);
		lblTotalScore.setText("0");
		
		Label lblRoundScoreInfo = new Label(shlSkunkGame, SWT.NONE);
		lblRoundScoreInfo.setText("Total Round Score:");
		lblRoundScoreInfo.setBounds(678, 580, 133, 20);
		
		Label lblRoundScore = new Label(shlSkunkGame, SWT.NONE);
		lblRoundScore.setText("0");
		lblRoundScore.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		lblRoundScore.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		lblRoundScore.setAlignment(SWT.CENTER);
		lblRoundScore.setBounds(824, 579, 133, 20);
		
		Label label_2 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(10, 207, 1001, 2);
		
		Group grpOptions = new Group(shlSkunkGame, SWT.NONE);
		grpOptions.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		grpOptions.setText("Options");
		grpOptions.setBounds(86, 226, 224, 150);
		
		Button btnNewGame = new Button(grpOptions, SWT.BORDER | SWT.CENTER);
		
		btnNewGame.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnNewGame.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		btnNewGame.setBounds(25, 35, 173, 30);
		btnNewGame.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD | SWT.ITALIC));
		btnNewGame.setText("New Game");
		
		Button btnNextRound = new Button(grpOptions, SWT.BORDER | SWT.CENTER);
		
		btnNextRound.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnNextRound.setEnabled(false);
		btnNextRound.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		btnNextRound.setBounds(25, 71, 173, 30);
		btnNextRound.setText("Next Round");
		btnNextRound.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD | SWT.ITALIC));
				
		Button btnRollDice = new Button(grpOptions, SWT.BORDER | SWT.CENTER);
		
		btnRollDice.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnRollDice.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		btnRollDice.setEnabled(false);
		btnRollDice.setText("Roll Dice");
		btnRollDice.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD | SWT.ITALIC));
		btnRollDice.setBounds(25, 110, 173, 30);
		
		Combo cmbQuantityOfDices = new Combo(shlSkunkGame, SWT.NONE);
		
		cmbQuantityOfDices.setItems(new String[] {"1", "2", "3"});
		cmbQuantityOfDices.setBounds(148, 412, 97, 28);
		cmbQuantityOfDices.select(1);
		
		//Get the quantity of dices to play at once
		this.quantityOfDices = Integer.parseInt(cmbQuantityOfDices.getText());
		
		Label lblNewLabel = new Label(shlSkunkGame, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		lblNewLabel.setBounds(124, 386, 149, 20);
		lblNewLabel.setText("Quantity of Dices");
		
		Label label_3 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(10, 454, 410, 2);
		
		Group grpResults = new Group(shlSkunkGame, SWT.NONE);
		grpResults.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
		grpResults.setText("Results");
		grpResults.setBounds(20, 456, 393, 144);
		
		Label lblDiceResult_1 = new Label(grpResults, SWT.NONE);
		lblDiceResult_1.setImage(SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/dice_face_6.png"));
		lblDiceResult_1.setAlignment(SWT.CENTER);
		lblDiceResult_1.setBounds(44, 37, 86, 86);
		
		Label lblDiceResult_2 = new Label(grpResults, SWT.NONE);
		lblDiceResult_2.setImage(SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/dice_face_1.png"));
		lblDiceResult_2.setAlignment(SWT.CENTER);
		lblDiceResult_2.setBounds(151, 37, 86, 86);
		
		Label lblDiceResult_3 = new Label(grpResults, SWT.NONE);
		lblDiceResult_3.setVisible(false);
		lblDiceResult_3.setImage(SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/dice_face_6.png"));
		lblDiceResult_3.setAlignment(SWT.CENTER);
		lblDiceResult_3.setBounds(257, 37, 86, 86);
		
		//int dicePosition = getDicePosition(grpResults.getBounds().width,lblDiceResult_1.getBounds().width,quantityOfDices, 1);
		//lblDiceResult_1.setBounds(dicePosition, lblDiceResult_1.getBounds().y, lblDiceResult_1.getBounds().width, lblDiceResult_1.getBounds().height);
		
		//dicePosition = getDicePosition(grpResults.getBounds().width,lblDiceResult_2.getBounds().width,quantityOfDices, 2);
		//lblDiceResult_1.setBounds(dicePosition, lblDiceResult_2.getBounds().y, lblDiceResult_2.getBounds().width, lblDiceResult_2.getBounds().height);
		
		//dicePosition = getDicePosition(grpResults.getBounds().width,lblDiceResult_3.getBounds().width,quantityOfDices, 3);
		//lblDiceResult_1.setBounds(dicePosition, lblDiceResult_3.getBounds().y, lblDiceResult_3.getBounds().width, lblDiceResult_3.getBounds().height);
		
		
		Label label_4 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.VERTICAL);
		label_4.setBounds(10, 207, 2, 405);
		
		Label label_5 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(10, 611, 1001, 2);
		
		Label label_6 = new Label(shlSkunkGame, SWT.SEPARATOR | SWT.VERTICAL);
		label_6.setBounds(1011, 207, 2, 405);
		
		
		customSkunkGame = new CustomSkunkGame(
					btnNewGame,
					btnNextRound,
					btnRollDice,
					cmbQuantityOfDices,
					lblTotalScore,
					lblRoundScore,
					shlSkunkGame
				);
		
		/**Window events**/
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startNewGame();
			   
			}

			private void startNewGame() {
				//Create a instance of a Skunk class
				createGameInstance();

			   btnRollDice.setEnabled(true);
			   lblRoundScore.setText(Integer.toString(skunk.getRoundScore()));
			   lblTotalScore.setText(Integer.toString(skunk.getGameScore()));
			   
			   //Remove all items from the table.
			   table.removeAll();
			   
			}
		});
		
		btnRollDice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hideAllDices();
				
				//Disable selection of dices
				customSkunkGame.disableQuantityOfDicesSeletion(false);
				
				ArrayList<Integer> dices = skunk.rollDice();
				Iterator<Integer> iteratorDice = dices.iterator();
				
				int diceFace= 0;
				int dicePosition = 0;
				while(iteratorDice.hasNext()) {
					diceFace = iteratorDice.next();
					dicePosition++;
					
					showDice(dicePosition,diceFace);
					
				}
				
				setPointsOnTable();
				
				if(skunk.getRollPoints() == 0) {
					btnNextRound.setEnabled(false);
					skunk.nextRound();
				}else {
					btnNextRound.setEnabled(true);
				}
				
				//Block buttons
				if(skunk.getRound() == SkunkRound.End)
				{
					customSkunkGame.endGame(skunk.getGameScore());
					
				}	
			}
			
			private void setPointsOnTable() {
				lblRoundScore.setText(Integer.toString(skunk.getRoundScore()));
				lblTotalScore.setText(Integer.toString(skunk.getGameScore()));
				
				//Always add a new item to the table
				if(skunk.getRound() == SkunkRound.First || skunk.getRoundQuantityOfRolls() > table.getItemCount()) {
					TableItem item = new TableItem(table, SWT.CENTER);
					item.setText(skunk.getRound().ordinal(), Integer.toString(skunk.getRollPoints()));
				}
				else {
					//Update table item 
					TableItem item = table.getItem((skunk.getRoundQuantityOfRolls() -1));
					item.setText(skunk.getRound().ordinal(), Integer.toString(skunk.getRollPoints()));
				}
				
			}
			
			private void hideAllDices() {
				lblDiceResult_1.setVisible(false);
				lblDiceResult_2.setVisible(false);
				lblDiceResult_3.setVisible(false);
			}
			
			private void showDice(int dicePosition, int diceFace) {
				
				
				
				Image image = getImage(diceFace);
				switch(dicePosition)
				{
					case 1:
						lblDiceResult_1.setImage(image);
						lblDiceResult_1.setVisible(true);
						break;
					case 2:
						lblDiceResult_2.setImage(image);
						lblDiceResult_2.setVisible(true);
						break;
					case 3:
						lblDiceResult_3.setImage(image);
						lblDiceResult_3.setVisible(true);
						break;
				}
			}
			
			private Image getImage(int diceFace) {
				return SWTResourceManager.getImage(SkunkGame.class, "/org/eclipse/wb/swt/dice_face_" + diceFace + ".png");
			}
		});
		
		cmbQuantityOfDices.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				//Get the quantity of dices to play at once
				quantityOfDices = Integer.parseInt(cmbQuantityOfDices.getText());
				skunk.setQuantityOfDices(quantityOfDices);
				
			}
		});
		
		btnNextRound.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				skunk.nextRound();
				
				btnNextRound.setEnabled(false);
				
				if(skunk.getRound() == SkunkRound.End)
				{
					customSkunkGame.endGame(skunk.getGameScore());
						
				}
				
				
			}

		});
	}
}
