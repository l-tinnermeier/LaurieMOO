package utilites;

/*
 * A utility class that is used with the RandomMooValue class to note a number's frequency
 * @author Luke Tinnermeier
 * @date 17/2/2025
 * @version 1.0
 * @file Number.java
 */
public class Number {
	public int number = 0;
	public int initalFrequency = 0;
	public int checkedFrequency = 0;
	
	/*
	 * getNumber()
	 * Returns the number set at creation 
	 * @returns number, an integer
	 */
	public int getNumber() {
		return number;
	}
	
	/*
	 * getInitalFrequency()
	 * Returns the amount of times the number is initially found in the number to be guessed
	 */
	public int getInitalFrequency() {
		return initalFrequency;
	}
	
	/*
	 * getCheckedFrequency()
	 * Returns the amount of times the number is found in the user's guess
	 * @returns checkedFrequency, an integer
	 */
	public int getCheckedFrequency() {
		return checkedFrequency;
	}
	
	/*
	 * setNumber()
	 * Sets the number at creation
	 * @param input, an integer used to set the number housed in the object
	 */
	public void setNumber(int input) {
		number = input;
	}
	
	/*
	 * incrementInitialFrequency()
	 * Increases the amount of times a number is found in the number to be guessed.
	 * This is used when checking the number to be guessed for validity 
	 */
	public void incrementInitalFrequency() {
		initalFrequency++;
	}
	
	/*
	 * incrementCheckedFrequency()
	 * Increases the amount of times a number is found in the user's guess
	 * Only increases when a number in the user's guess matches the number to be guessed
	 */
	public void incrementCheckedFrequency() {
		checkedFrequency++;
	}

}
