package game;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import utilites.Number;

/*
 * The class that holds all of the logic for generating, checking, and returning the number to be guessed
 * @author Luke Tinnermeier
 * @date 17/2/2025
 * @version 1.2
 * @file RandomMooValue.java
 */
public class RandomMooValue {

	public int numToBeGuessed = 1000;
	public int rangeMax = 9999;
	
	public ArrayList<Integer> numToBeGuessedArray = new ArrayList<>();
	public ArrayList<Number> numFrequency = new ArrayList<>();
	
	/*
	 * RandomMooValue()
	 * A constructor for the class
	 */
	public RandomMooValue() {
		
	}
	
	/*
	 * getBigMooCount()
	 * Checks the user's answer for the amount of "MOOS" it has by comparing each time it appears to the frequency found in the setFrequency() function
	 * @param userGuess, an ArrayList which contains the user's guess
	 * @return moos, the amount of "MOOS" that appears in the user's answer
	 */
	public int getBigMooCount(ArrayList<Integer> userGuess) {
		
		int moos = 0;
		
		/*
		 * Loops through the number to be guessed array and checks if the digit at index i matches its corresponding digit in the user's guess
		 * If so, add 1 to the moo counter and its found frequency
		 * If not, do nothing
		 */
		for (int i = 0; i < numToBeGuessedArray.size(); i++) {
			if (numToBeGuessedArray.get(i) == userGuess.get(i)) {
				for (int j = 0; j < numFrequency.size(); j++) {
					if (numFrequency.get(j).getNumber() == numToBeGuessedArray.get(i) && numFrequency.get(j).getInitalFrequency() > numFrequency.get(j).getCheckedFrequency()) {
						moos++;
						numFrequency.get(j).incrementCheckedFrequency();
						
						System.out.print(numFrequency.get(j).getNumber());
						System.out.print(numFrequency.get(j).initalFrequency);
						System.out.print(numFrequency.get(j).checkedFrequency);
					}
				}

			}
		}
		
		return moos;
	}
	
	/*
	 * getLittleMooCount()
	 * Checks the user's answer for the amount of "moos" it has by comparing each time it appears to the frequency found in the setFrequency() function
	 * @param userGuess, an arrayList version of the user's guess (so the 0's are properly in place)
	 * @return moos, the amount of little "moos" are in the final answer
	 */
	public int getLittleMooCount(ArrayList<Integer> userGuess) {
		
		int moos = 0;
		
		/*
		 * Loops through the number to be guessed array
		 * Checks to see if a particular digit of the user's guess appears at its corresponding index in the number needed to be guessed
		 * If so, notes the index to be checked later
		 * It then loops through the numberFrequency array
		 * It checks if the numberFrequency number matches the user's number at index i
		 * If so, then it checks if the number has already been found the maximum amount of times
		 * 1. If so, then it does nothing
		 * 2. If not, then it adds one to the moo counter and adds one to its frequency
		 */
		for (int i = 0; i < numToBeGuessedArray.size(); i++) {
			System.out.println("");
			System.out.println("Current Final Number: " + numToBeGuessedArray.get(i));
			System.out.println("Current User Number Being Checked: " + userGuess.get(i));
			
			int numberIndex = 0;
			int numberValue = 0;
			boolean contains = false;
	
			 
			if (numToBeGuessedArray.contains(userGuess.get(i))) {
				numberIndex = i;
				numberValue = userGuess.get(i);
				contains = true;
				
				System.out.println("Number Found");
				System.out.println("Number index: " + numberIndex);
				System.out.println("Number value: " + numberValue);
			}
			
			if (contains && numberValue != numToBeGuessedArray.get(i)) {
				System.out.println("Number Match!");
				
				for (int j = 0; j < numFrequency.size(); j++) {
					
					if (numFrequency.get(j).getNumber() == numberValue) {
						
						System.out.println("Number Frequency Value: " + numFrequency.get(j).getNumber() + " | Current Comparison: " + numberValue);
						
						if (numFrequency.get(j).getCheckedFrequency() < numFrequency.get(j).getInitalFrequency()) {
							moos++;
							numFrequency.get(j).incrementCheckedFrequency();
							
							System.out.println("Number (Checked) Frequency:" + numFrequency.get(j).getCheckedFrequency());
							System.out.println("Number (Inital) Frequency:" + numFrequency.get(j).getInitalFrequency());
							System.out.println("Moos: " + moos);
							

						} else {
							System.out.println("All moos found for this number");
						}
					}
				}
			}
		}
		
		return moos;
		
		/*
		int moos = 0;
		
		for (int i = 0; i < numToBeGuessedArray.size(); i++) {
			
			for (int num : numToBeGuessedArray) {
				if (userGuess.get(i) == num) {
					
				}
			}
			
			if (numToBeGuessedArray.contains(userGuess.get(i)) && numToBeGuessedArray.get(i) != userGuess.get(i)) {
				System.out.print(" Number found on iteration " + i + " ");
				for (int j = 0; j < numFrequency.size(); j++) {
					if (numFrequency.get(j).getNumber() == numToBeGuessedArray.get(i) && numFrequency.get(j).getInitalFrequency() > numFrequency.get(j).getCheckedFrequency() && numFrequency.get(j).number == userGuess.get(i)) {
						moos++;
						numFrequency.get(j).incrementCheckedFre quency();
						
						System.out.print("J Value: " + j + " ");
						System.out.print(numFrequency.get(j).getNumber());
						System.out.print(numFrequency.get(j).initalFrequency);
						System.out.print(numFrequency.get(j).checkedFrequency);
					}
				}
			}	
		}
		
		return moos;
		*/
	}
	
	
	/*
	 * getSecretValue()
	 * @return returns the number to be guessed as an integer
	 */
	public int getSecretValue() {
		return numToBeGuessed;
	}
	
	/*
	 * getSecretValueArray()
	 * @return returns the secret value as an ArrayList
	 */
	public ArrayList<Integer> getSecretValueArray() {
		return numToBeGuessedArray;
	}
	
	/*
	 * setSecretValue()
	 * Sets the secret value within the given range
	 * @param range, an integer, used to tell the random number generator what the maximum number could be
	 * @return true if the number was set successfully
	 */
	public boolean setSecretValue(int range) {
		Random randomGenerator = new Random();
		numToBeGuessed = randomGenerator.nextInt(range);
		

		String roundGuessStr = numToBeGuessed + "";
		
		/*
		 * Takes the generated number and converts it to an array
		 */
		for (int i = 0; i < roundGuessStr.length(); i++) {
			char specificChar = roundGuessStr.charAt(i);
			
			int conversion = Character.getNumericValue(specificChar);
			numToBeGuessedArray.add(conversion);
		}
		
		while (numToBeGuessedArray.size() < 4) {
			numToBeGuessedArray.add(0, 0);
		}
		
		
		return true;
	}
	
	/*
	 * isCorrectGuess()
	 * Checks if the user's guess is correct
	 * @param guess, an integer used to compare to the number to be guessed
	 * @return true if the answer is correct, false if not
	 */
	public boolean isCorrectGuess(int guess) {
		if (guess == numToBeGuessed) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * getSecretValueString()
	 * Converts the array version of the number to be guessed to a string in order to be displayed
	 * @return secretValueString, a string version of the number needed to be guessed (with 0's if needed)
	 */
	public String getSecretValueString() {
		String secretValueString = "";
		
		/*
		 * Loops through the array and appends each digit to a string
		 */
		for (int i = 0; i < numToBeGuessedArray.size(); i++) {
			secretValueString += numToBeGuessedArray.get(i) + "";
		}
			
		return secretValueString;
	}
	
	/*
	 * resetGame()
	 * Resets the game function to its default values
	 */
	public void resetGame() {
		numToBeGuessed = 1000;
		numToBeGuessedArray.clear();
		numFrequency.clear();
	}
	
	/*
	 * setFrequency()
	 * Checks to see how many times a number appears in:
	 * 1. The number that the user needs to guess
	 * 2. The number that the user inputed
	 * This allows for accurate checking of the amount of "moos" and "MOOS"
	 */
	public void setFrequency() {
		numFrequency.clear();
		
		List<Integer> UniqueNumbers = numToBeGuessedArray.stream().distinct().collect(Collectors.toList());
		//System.out.print(UniqueNumbers);
		
		/*
		 * Creates a new instance of the Number class for each digit in the array & adds it to an arrayList called numFrequency
		 */
		for(int i = 0; i < UniqueNumbers.size(); i++) {
			Number number = new Number();
			number.setNumber(UniqueNumbers.get(i));
			numFrequency.add(number);
		}

		/*
		 * Notes the frequency of each number in the number to be guessed & adds it to the corresponding Number object
		 */
		int numbersChecked = 0;
		while (numbersChecked < 4) {
			if (numbersChecked < 4) {
				for (int i = 0; i < numFrequency.size(); i++) {
					if (numToBeGuessedArray.contains(numFrequency.get(i).getNumber())) {
						numFrequency.get(i).incrementInitalFrequency();
						numbersChecked++;
					}
				}
			} else {
				break;
			}
		}

		/*
		 * Loops through the same number and sees how many times a particular number appears & increments its frequency
		 */
		for (int i = 0; i < numFrequency.size(); i++) {
			System.out.print(numFrequency.get(i).getNumber());
			System.out.print(numFrequency.get(i).getInitalFrequency());
		}
		
		for(int i = 0; i < numToBeGuessedArray.size(); i++) {
			System.out.print(numToBeGuessedArray.get(i));
		}


		
	}
	
	/*
	 * setKnownSecretValue()
	 * A function that takes a known input and sets the secret value to it.
	 * If the number is out of range, then don't set the secret value and do nothing.
	 * @param n, an integer, used as the number to set
	 * @return true/false, a boolean that returns true if the number was set successfully, false if not
	 */
	public boolean setKnownSecretValue(int n) {
		
		/*
		 * Checking to see if the number is in range (9999) or not
		 * 1. If the number is in range, then check if it is 4-digits or not, if not, then add zeros to make it four
		 * 2. If it is 4-digits, go ahead and add each character to the number to be guessed array
		 */
		if (numToBeGuessed < rangeMax) {
			numToBeGuessed = n;
			
			numToBeGuessedArray.clear();
			String roundGuessStr = numToBeGuessed + "";
			
			for (int i = 0; i < roundGuessStr.length(); i++) {
				char specificChar = roundGuessStr.charAt(i);
				
				int conversion = Character.getNumericValue(specificChar);
				numToBeGuessedArray.add(conversion);
			}
			
			while (numToBeGuessedArray.size() < 4) {
				numToBeGuessedArray.add(0, 0);
			}
			
			return true;
		} else {
			return false;
		}
	}
}
