package game;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.util.ArrayList;
import java.util.Random;

/*
 * The class that (mostly) hosts all of the logic behind the game
 * @author Luke Tinnermeier
 * @date 17/2/2025
 * @version 1.4
 * @file GameLogic.java
 */
public class GameLogic {
	
	public static RandomMooValue game = new RandomMooValue();
	
	public static int userInputInt = 0;
	public static int numToBeGuessed = 0;
	public static int roundGuesses = 10;
	public static int guessRange = 9999;
	
	public static ArrayList<JLabel> moosDispArray = new ArrayList<>();
	
	public static ArrayList<Integer> userInputArray = new ArrayList<>();
	public static ArrayList<Integer> numToBeGuessedArray = new ArrayList<>();
	
	public static String userInputString = "";
	
	/*
	 * setupGame()
	 * Sets the value to be guessed by the user & displays it on the GUI
	 * @param numberOutput, a JLabel used to display the number to be guessed. For debugging purposes only
	 */
	public static void setupGame(JLabel numberOutput) {
		game.setSecretValue(guessRange);
		
		numToBeGuessed = game.getSecretValue();
		
		numberOutput.setText(game.getSecretValueString());
		
	}
	
	/*
	 * addMoosDisp()
	 * Grabs all of the labels where the "moos" are displayed & puts them into an array
	 * @param label1, a JLabel that can display a "moo," "MOO," or ""
	 * @param label2, a JLabel that can display a "moo," "MOO," or ""
	 * @param label3, a JLabel that can display a "moo," "MOO," or ""
	 * @param label4, a JLabel that can display a "moo," "MOO," or ""
	 */
	public static void addMoosDisp(JLabel label1, JLabel label2, JLabel label3, JLabel label4) {
		moosDispArray.add(label1);
		moosDispArray.add(label2);
		moosDispArray.add(label3);
		moosDispArray.add(label4);
	}
	
	/*
	 * parseUserAnswer()
	 * Takes the user input from the GameDisp file, converts it to a number, and displays their guess
	 * @param input, a JTextfield, used to get the user's input
	 * @param output, a JLabel, used to display the user's guess. For debugging purposes only
	 * @return boolean, returns TRUE if the user's input was successfully parsed, FALSE if not
	 */
	public static boolean parseUserAnswer(JTextField input, JLabel output) {
		
		userInputArray.clear();
		
		userInputString = input.getText();
		
		/*
		 * Checks if the user entered a four digit number, if so, then it adds every value to an arrayList.
		 * 1. If its out of bounds, or not a number, then return an error and do NOT submit the guess
		 * 2. If it is in bounds, then check if it is less than four
		 * 	1. If it is less than four, add zeros to the array to make it a four-digit number
		 * 	2. If not, add no zeros and just add the each separate digit to an array
		 */
		try {
			if (userInputString.length() <= 4) {
				userInputInt = Integer.parseInt(userInputString);
				
				for (int i = 0; i < userInputString.length(); i++) {
					char specificChar = userInputString.charAt(i);
					int conversion = Character.getNumericValue(specificChar);
					
					userInputArray.add(conversion);
				}
				
				while (userInputArray.size() < 4) {
					userInputArray.add(0, 0);
				}
				
				output.setText(userInputString);
			} 
			
			game.setFrequency();
			
			roundGuesses--;
			
			return true;
			
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number!");
			return false;
		}
	}
	
	/*
	 * checkUserAnswer()
	 * Takes the user input that was parsed in the above function to check if it matches the number to be guessed or not
	 * @param numMOOSDisp, a JLabel that displays the total number of "MOOS" in the user's input
	 * @param numMoosDisp, a JLabel that displays the total number of "moos" in the user's input
	 * @param finalDisp, a JLabel that says whether the user won or not. Is passed into the addMoos() method
	 * @param playAgainBtn, a JButton that is visible if the user won, invisible if not
	 * @param userInputLabel, a JLabel that displays the current amount of guesses. For debugging purposes only
	 * @param userInput, a JTextField that is used to grab the user's input
	 */
	public static void checkUserAnswer(JLabel numMOOSDisp, JLabel numMoosDisp, JLabel finalDisp, JButton playAgainBtn, JLabel userInputLabel, JTextField userInput, JFrame frame, JDialog log, JLabel logLabel) {
		
		/*
		 * Checks if there are still guesses left, and if there are, then:
		 * 1. Checks if the user guessed the right number
		 * 	1. If so, then gets the game ready to be reset
		 * 	2. If not, then move on to the addMoos() function & do nothing extra
		 * 2. If they did not, then reset the game and show that the user lost
		 */
		if (roundGuesses != 0) {
			if (userInputInt == numToBeGuessed) {
				playAgainBtn.setVisible(true);
				userInputLabel.setVisible(false);
				userInput.setVisible(false);
				addMoos(finalDisp, Integer.parseInt(numMOOSDisp.getText()), Integer.parseInt(numMoosDisp.getText()), frame);
			      log.setLocationRelativeTo(frame);
	              log.setVisible(true);
	              logLabel.setText(numToBeGuessed + "");
			} else {
				numMOOSDisp.setText(game.getBigMooCount(userInputArray) + "");
				numMoosDisp.setText(game.getLittleMooCount(userInputArray) + "");
				addMoos(finalDisp, Integer.parseInt(numMOOSDisp.getText()), Integer.parseInt(numMoosDisp.getText()), frame);
			}
		} else {
		      log.setLocationRelativeTo(frame);
              log.setVisible(true);
              logLabel.setText(numToBeGuessed + "");
			playAgainBtn.setVisible(true);
			userInputLabel.setVisible(false);
			userInput.setVisible(false);
			addMoos(finalDisp, Integer.parseInt(numMOOSDisp.getText()), Integer.parseInt(numMoosDisp.getText()), frame);

		}
	}
	
	/*
	 * addMoos()
	 * Used to display a certain amount of "MOOS" or "moos" above the cows in the GUI
	 * @description This is used to let the user know whether they have a number correct or not
	 * @param finalDisp, a JLabel used to display whether the user had every number matching or none
	 * @param bigMOOS, an integer that tells how many MOOS need to be displayed
	 * @param littleMoos, an integer that tells how many moos need to be displayed
	 * @param frame, a JFrame, needed to allow the window to refresh itself to display new information
	 */
	public static void addMoos(JLabel finalDisp, int bigMOOS, int littleMoos, JFrame frame) {
		ArrayList<Integer> moosArrayIndexes = new ArrayList<>();
		
		int bigMooCount = bigMOOS;
		int littleMooCount = littleMoos;
		
		System.out.println("Big MOOS:" + bigMooCount);
		System.out.println("Little moos: " + littleMooCount);

		
		/*
		 * This checks if the there are any big "MOOS" and little "moos" in the user's input. If so,
		 * it adds them to the GUI, starting with the big "MOOS," then moving onto the little "moos."
		 * If there are zero "moos" or "MOOS," then it checks one of three conditions:
		 * 1. If the round guesses are 0, which displays "Boo hoo, no LaurieMOO" & the correct number
		 * 2. If the user guessed the number correctly, then display "LaurieMOO"
		 * 3. If there are no moos and the game isn't over, then display "you only hear cowbells"
		 */
		if (bigMooCount + littleMooCount != 0) {
			for (int i = 0; i < 4; i++) {
				moosArrayIndexes.add(i);
				moosDispArray.get(i).setText("");
			}
			
			System.out.println(moosArrayIndexes);
			
			while (moosArrayIndexes.size() > 0) {
				int numberIndex = generateRandom(moosArrayIndexes.size());
				
				if (bigMooCount > 0) {
					moosDispArray.get(moosArrayIndexes.get(numberIndex)).setText("MOO");
					System.out.println("MOO in index " + numberIndex);
					bigMooCount--;
				} else if (littleMooCount > 0) {
					moosDispArray.get(moosArrayIndexes.get(numberIndex)).setText("moo");
					littleMooCount--;
					System.out.println("moo in index " + numberIndex);
				} else {
					moosDispArray.get(moosArrayIndexes.get(numberIndex)).setText("");
					System.out.println("null in index " + numberIndex);
				}
				
				moosArrayIndexes.remove(numberIndex);
				System.out.println(moosArrayIndexes);
			}
			
			finalDisp.setText("");
			
			if (roundGuesses == 0) {
				finalDisp.setText("Boo hoo, no LaurieMOO" + "\n the number was: " + numToBeGuessed);
			} 
			
		} else {
			 if (roundGuesses == 0) {
					finalDisp.setText("Boo hoo, no LaurieMOO" + "\n the number was: " + numToBeGuessed);
				} else if (userInputInt == numToBeGuessed) {
					finalDisp.setText("LaruieMOOOOOO");
				} else if (bigMooCount + littleMooCount == 0 && roundGuesses != 0) {
				finalDisp.setText("All you hear are cowbells...");
				for (int i = 0; i < moosDispArray.size(); i++) {
					moosDispArray.get(i).setText("");
				}
			}

		}
		
		frame.revalidate();
		frame.repaint();

	}
	
	/*
	 * resetGame()
	 * A reset function, used to reset the data of the game to allow for a new game
	 * @param playAgainBtn, a JButton that is set invisible when the user clicks the button to play again
	 * @param numDisp, a JLabel used to display the new random guess. For debugging purposes only
	 * @param guessContainer, a JTextArea used to display the user's guesses. Reset upon this function call
	 * @param userInputLabel, a JLabel that is turned visible upon the game being reset via this function call
	 * @param userInput, a JTextArea used to get the user's guess. It's contents are set to blank upon this function call
	 * @param finalDisp, a JLabel used to display whether the user guessed all or none of the numbers correctly. Is set to blank upon this function call
	 * @param frame, a JFrame used to add new information to the screen
	 */
	public static void playAgain(JButton playAgainBtn, JLabel numDisp, JTextArea guessContainer, JLabel userInputLabel, JTextField userInput, JLabel finalDisp, JFrame frame) {
		game.resetGame();
		roundGuesses = 10;
		// MAY DELETE ^^^
		playAgainBtn.setVisible(false);
		userInput.setVisible(true);
		userInputLabel.setVisible(true);
		game.setSecretValue(guessRange);
		numToBeGuessed = game.getSecretValue();
		numToBeGuessedArray = game.getSecretValueArray();
		numDisp.setText(game.getSecretValueString());
		guessContainer.setText("");
		finalDisp.setText("");
		
		for (int i = 0; i < moosDispArray.size(); i++) {
			moosDispArray.get(i).setText("");
		}
		
		frame.revalidate();
		frame.repaint();
	}
	
	/*
	 * addGuess()
	 * A small helper function that adds the user's guess to the UI
	 * @param frame, a JFrame that allows new information to be added
	 * @param textArea, a JTextArea which houses the user's guesses
	 * @param guessesAmountDisp, a JLabel that displays how many guesses the user has left
	 */
	public static void addGuess(JFrame frame, JTextArea textArea, JLabel guessesAmountDisp) {
		textArea.append(userInputString + "\n");
		guessesAmountDisp.setText(roundGuesses + "");
		
		frame.revalidate();
		frame.repaint();
	}
	
	/*
	 * generateRandom()
	 * A small helper function that allows a random number to be generated
	 * @param range, an integer that defines the range the random number generator uses
	 * @return returns a "randomly generated" number
	 */
	
	public static int generateRandom(int range) {
		Random randomGenerator = new Random();
		
		int number = randomGenerator.nextInt(range);
		
		return number;
	}
}
