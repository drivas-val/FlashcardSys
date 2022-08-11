package leinad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class LearnScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel flashcard;
	JButton flipButton, correctButton, wrongButton, endButton;
	Frame frame;
	JPanel panel;
	
	Box box = new Box();
	
	/**
	 * Creates screen where flashcards can be learned.
	 */
		public void showAll(){
		//Calls a random flashcard using exponential probability from one of 5 boxes
		box.boxOne();
		box.pickFlashcard();
		
		//Create frame and panel
		frame = new Frame();
		panel = new JPanel();
		
		frame.setSize(480, 375);
		
		//Black outline border on the flashcard
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		//Create JPanel as flashcard
		flashcard = new JLabel(Box.currentKey);
	    flashcard.setFont(new Font("Noto Serif Display", Font.BOLD, 15));
	    flashcard.setAlignmentX(CENTER_ALIGNMENT);
	    flashcard.setBorder(blackline);
	    
	    //Set margin on the flashcard
	    Border border = flashcard.getBorder();
	    Border margin = new EmptyBorder(80,80,80,80);
	    flashcard.setBorder(new CompoundBorder(border, margin));
	    
	    //Flip button for flipping flashcard
	    flipButton = new JButton();
	    flipButton.setPreferredSize(new Dimension(90, 30));
	    flipButton.setText("Flip");
	    flipButton.setAlignmentX(CENTER_ALIGNMENT);
	    flipButton.addActionListener(this);
	    
	    //Correct button for getting a flashcard right
	    correctButton = new JButton();
	    correctButton.setPreferredSize(new Dimension(90, 30));
	    correctButton.setText("Correct");
	    correctButton.setAlignmentX(CENTER_ALIGNMENT);
	    correctButton.addActionListener(this);
	    
	    //Wrong button for getting a flashcard wrong
	    wrongButton = new JButton();
	    wrongButton.setPreferredSize(new Dimension(90, 30));
	    wrongButton.setText("Wrong");
	    wrongButton.setAlignmentX(CENTER_ALIGNMENT);
	    wrongButton.addActionListener(this);
	    
	    //End button for terminating the session
	    endButton = new JButton();
	    endButton.setPreferredSize(new Dimension(90, 30));
	    endButton.setText("Exit");
	    endButton.setAlignmentX(CENTER_ALIGNMENT);
	    endButton.addActionListener(this);
	    
	    //Panel Layout
	    panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    panel.add(flashcard);
	    panel.add(flipButton);
	    panel.add(correctButton);
	    panel.add(wrongButton);
	    panel.add(endButton);
	    panel.repaint();
	    panel.revalidate();
	    
	    //Add panel to frame
	    frame.add(panel);		
	}
		
	/**
	 * Action Listener
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Flips the flashcard by changing the JLabel text to its key/value counterpart
		if (e.getSource()==flipButton) {
			if (flashcard.getText().equals(Box.currentKey)) {
				flashcard.setText(Box.currentValue);
			}
			else {
				flashcard.setText(Box.currentKey);
			}
		}
		
		//Marks a flashcard as correct by moving the flashcard to the next box
		if (e.getSource()==correctButton) {
			//1 -> 2
			if (box.boxOne.containsKey(Box.currentKey)) {
				box.boxTwo.put(Box.currentKey, Box.currentValue);
				box.boxOne.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			//2 -> 3
			if (box.boxTwo.containsKey(Box.currentKey)) {
				box.boxThree.put(Box.currentKey, Box.currentValue);
				box.boxTwo.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			//3 -> 4
			if (box.boxThree.containsKey(Box.currentKey)) {
				box.boxFour.put(Box.currentKey, Box.currentValue);
				box.boxThree.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			// 4 -> 5
			if (box.boxFour.containsKey(Box.currentKey)) {
				box.boxFive.put(Box.currentKey, Box.currentValue);
				box.boxFour.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			//If the flashcard cannot move forward it will simply pick a new flashcard (edgecase for box 5)
			if (box.boxFive.containsKey(Box.currentKey)) {
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			
		}
		
		//Marks a flashcard incorrect by moving the flashcard back one box
		if (e.getSource()==wrongButton) {
			//If the flashcard cannot move backwards it will simply pick a new flashcard (edgcase for box 1)
			if (box.boxOne.containsKey(Box.currentKey)) {
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			// 2 -> 1
			if (box.boxTwo.containsKey(Box.currentKey)) {
				box.boxOne.put(Box.currentKey, Box.currentValue);
				box.boxTwo.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			// 3 -> 2
			if (box.boxThree.containsKey(Box.currentKey)) {
				box.boxTwo.put(Box.currentKey, Box.currentValue);
				box.boxThree.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			// 4 -> 3
			if (box.boxFour.containsKey(Box.currentKey)) {
				box.boxThree.put(Box.currentKey, Box.currentValue);
				box.boxFour.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
			// 5 -> 4
			if (box.boxFive.containsKey(Box.currentKey)) {
				box.boxFour.put(Box.currentKey, Box.currentValue);
				box.boxFive.remove(Box.currentKey);
				box.pickFlashcard();
				flashcard.setText(Box.currentKey);
			}
		}
		//If the exit button is pressed the session terminates and the window closes
		if (e.getSource()==endButton) {
			frame.dispose();
			
		}
	}
}
