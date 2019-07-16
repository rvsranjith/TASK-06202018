package app;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.*;

public class ApplicationMethods {
	
	//analyzeText()
	//Master function for event listener
	//-----------------------------------------------------------------------
	public static void analyzeText(TextArea userInputTextArea, Label totalWordsTarget,
					Label uniqueWordsTarget, Label totalCharsTarget, VBox wordFrequencyTarget) {
		
		String userInputAsOneString = userInputTextArea.getText();
		countCharsAndUpdateLabel(userInputAsOneString, totalCharsTarget); // <- 1. defined below
		
		//Check to see if user input is blank:
		if (userInputAsOneString.equals("") || userInputAsOneString.equals(" ")) {
			totalWordsTarget.setText("Please enter some text first.");
			uniqueWordsTarget.setText("");
			totalCharsTarget.setText("");
			
		} else {
		//------------------------------------------------------------------------
			
			ArrayList<String> words = new ArrayList<>();
			ArrayList<Integer> frequency = new ArrayList<>();
		
			userInputAsOneString = stripExtraCharactersFromWord(userInputAsOneString); // <- 2. defined below
		
			//Populate ArrayLists words and frequency:
			Scanner sc = new Scanner(userInputAsOneString);
			while (sc.hasNext()) {
				String temp = sc.next();
			
				if (words.contains(temp)) {
					int index = words.indexOf(temp);
					frequency.set(index, frequency.get(index) + 1);
				} else if (!words.contains(temp)) {
					words.add(temp);
					frequency.add(1);
				}
			}
			sc.close();
			
			int totalNumberOfWordsInUserInput = getTotalNumberOfWordsInUserInput(userInputTextArea); // <- 3. defined below
			int totalNumberOfUniqueWords = words.size();
			totalWordsTarget.setText("There are " + totalNumberOfWordsInUserInput + " words,");
			uniqueWordsTarget.setText(totalNumberOfUniqueWords + " of which are unique.");
			updateListOfWordFrequencies(words,frequency,wordFrequencyTarget); // <- 4. defined below
			
		}
	}
	
	
	
	
	//--1:
	//countCharsAndUpdateLabel()
	//Since most modern interfaces like Twitter, Facebook, and
	//Google count both the \n character and the space character as
	//characters toward their character limits, so will we:
	public static void countCharsAndUpdateLabel(String userInputAsOneString, Label targetLabel) {
		targetLabel.setText("There are " + userInputAsOneString.length() + " characters.");
	}
	
	
	//--2:
	//stripExtraCharactersFromWord()
	//When trying to count "unique" words we need to make sure the
	//computer sees "friend" "friend." and "friend?" as the same word
	//instead of three unique words:
	public static String stripExtraCharactersFromWord(String currentWord) {
		String output = "";
			
		String temp = currentWord.toLowerCase();
		if (temp.contains(",")) {
			temp = temp.replaceAll(",", "");
		}
		if (temp.contains("?")) {
			temp = temp.replaceAll("\\?", "");
		}
		if (temp.contains("!")) {
			temp = temp.replaceAll("!", "");
		}
		if (temp.contains(".")) {
			temp = temp.replaceAll("\\.", "");
		}
		if (temp.contains("\n")) {
			temp = temp.replaceAll("\n", " ");
		}
		if (temp.contains("\t")) {
			temp = temp.replaceAll("\t", " ");
		}
		if (temp.contains("\'")) {
			temp = temp.replaceAll("\'", "");
		}
		if (temp.contains("\"")) {
			temp = temp.replaceAll("\"", "");
		}
		if (temp.contains("(")) {
			temp = temp.replaceAll("\\(", "");
		}
		if (temp.contains(")")) {
			temp = temp.replaceAll("\\)", "");
		}
		if (temp.contains("  ")) {
			temp = temp.replaceAll("  ", " ");
		}
		if (temp.contains("   ")) {
			temp = temp.replaceAll("   ", " ");
		}
			
		output = temp;
			
		return output;
	}
	
	
	//--3:
	//getTotalNumberOfWordsInUserInput()
	//Returns a number of words in input given a TextArea node:
	public static int getTotalNumberOfWordsInUserInput(TextArea userInputTextArea) {
		int totalNumberOfWordsInUserInput = 0;
		
		String userInputAsOneString = userInputTextArea.getText();
		userInputAsOneString = userInputAsOneString.replaceAll("\n", " ");
		userInputAsOneString = userInputAsOneString.replaceAll("  ", " ");
		String[] userInputByWord = userInputAsOneString.split(" ");
		
		totalNumberOfWordsInUserInput = userInputByWord.length;
		
		return totalNumberOfWordsInUserInput;
	}
	
	//--4
	//updateListOfWordFrequencies()
	public static void updateListOfWordFrequencies(ArrayList<String> wordList, ArrayList<Integer> freqList, VBox targetVBox) {
		//If there are any nodes appended to the target VBox already, remove them:
		targetVBox.getChildren().removeAll(targetVBox.getChildren());
		
		//Get length of lists
		int lengthOfLists = wordList.size();
		
		//Create an HBox called row for every unique word
		//then append it. By using stackpanes, we can change
		//the background color based on its frequency number.
		for (int i = 0; i < lengthOfLists; i++) {
			HBox row = new HBox(10);
			String currentWord = wordList.get(i);
			int freqOfCurrentWord = freqList.get(i);
			
			StackPane wordBox = new StackPane();
			Label word = new Label(currentWord);
			word.setStyle("-fx-padding: 2");
			Label appears = new Label(" - ");
			String timeVar = "";
			if (freqOfCurrentWord == 1) {
				timeVar = " time.";
			} else if (freqOfCurrentWord > 1) {
				timeVar = " times.";
			}
			Label timeCount = new Label(Integer.toString(freqOfCurrentWord) + timeVar);
			
			wordBox.getChildren().add(word);
			row.getChildren().addAll(wordBox,appears,timeCount);
			
			if (freqOfCurrentWord < 2) {
				wordBox.setStyle("-fx-background-color: #ddeaff");
			} else if (freqOfCurrentWord == 2) {
				wordBox.setStyle("-fx-background-color: #ddfffb");
			}else if (freqOfCurrentWord >= 3 && freqOfCurrentWord < 6) {
				wordBox.setStyle("-fx-background-color: #fff6dd");
			} else if (freqOfCurrentWord >= 6) {
				wordBox.setStyle("-fx-background-color: #ff8484");
			}
			
			targetVBox.getChildren().add(row);
		}
		
	}
	
	
	
}
