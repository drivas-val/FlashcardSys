package leinad;

import java.util.HashMap;
import java.util.Random;

public class Box {
	//Creates 5 boxes as hashmaps
	
	HashMap<String, String> boxOne = new HashMap<String, String>();
	
	HashMap<String, String> boxTwo = new HashMap<String, String>();
	
	HashMap<String, String> boxThree = new HashMap<String, String>();
	
	HashMap<String, String> boxFour = new HashMap<String, String>();
	
	HashMap<String, String> boxFive = new HashMap<String, String>();
	
	CreateScreen cs = new CreateScreen();
	
	//Static variables to keep track of the current flashcard
	static String currentKey = "";
	static String currentValue = "";
	
	//Hashmap containing all original key-value pairs
	HashMap<String, String> flashcardDic = cs.getHashmap();
	
	/**
	 * Extracts original key,value pairs into box one
	 */
	public void boxOne() {
		
		for (HashMap.Entry<String, String> i : flashcardDic.entrySet()) {
			String key = (String)i.getKey();
			String value = (String)i.getValue();
			boxOne.put(key, value);
		}
	}
	
	/*
	 * Picks a random flashcard to extract the key/value
	 */
	public void pickFlashcard() {
		
		//Creates random integer from 0-31
		Random rand = new Random();
		int randInt = rand.nextInt(32);
		
		//Picks from box one (base case)
		if (randInt >= 15 && randInt <= 31 && boxOne.size() != 0) {
				Object[] keyArray = boxOne.keySet().toArray();
				Object randKey = keyArray[new Random().nextInt(keyArray.length)];
				currentKey = (String)randKey;
				currentValue = (String)boxOne.get(randKey);
		}
		//Picks new number if box one is empty
		else if (randInt >= 15 && randInt <= 31 && boxOne.size() == 0) {
			pickFlashcard();
		}
		//Picks from box two (2x less probabilty than box 1)
		if (randInt >= 7 && randInt <= 14 && boxTwo.size() != 0) {
			Object[] keyArray = boxTwo.keySet().toArray();
			Object randKey = keyArray[new Random().nextInt(keyArray.length)];
			currentKey = (String)randKey;
			currentValue = (String)boxTwo.get(randKey);
		}
		//Picks new number if box two is empty
		else if (randInt >= 7 && randInt <= 14 && boxTwo.size() == 0) {
			pickFlashcard();
		}
		//Picks from box three (4x less probabaility than box 1)
		if (randInt >= 3 && randInt <= 6 && boxThree.size() != 0) {
			Object[] keyArray = boxThree.keySet().toArray();
			Object randKey = keyArray[new Random().nextInt(keyArray.length)];
			currentKey = (String)randKey;
			currentValue = (String)boxThree.get(randKey);
		}
		//Picks new number if box three is empty
		else if (randInt >= 3 && randInt <= 6 && boxThree.size() == 0) {
			pickFlashcard();
		}
		//Picks from box four (8x less probability than box 1)
		if (randInt >= 1 && randInt <= 2 && boxFour.size() != 0) {
			Object[] keyArray = boxFour.keySet().toArray();
			Object randKey = keyArray[new Random().nextInt(keyArray.length)];
			currentKey = (String)randKey;
			currentValue = (String)boxFour.get(randKey);
		}
		//Picks new number if box four is empty
		else if (randInt >= 1 && randInt <= 2 && boxFour.size() == 0) {
			pickFlashcard();
		}
		//Picks from box five (16x less probability than box 1)
		if (randInt == 0 && boxFive.size() != 0){
			Object[] keyArray = boxFive.keySet().toArray();
			Object randKey = keyArray[new Random().nextInt(keyArray.length)];
			currentKey = (String)randKey;
			currentValue = (String)boxFive.get(randKey);
		}
		//Picks new number if box five is empty
		else if (randInt >= 1 && randInt <= 2 && boxFour.size() == 0) {
			pickFlashcard();
		}
	}
	
}

