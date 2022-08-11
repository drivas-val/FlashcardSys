package leinad;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//Hashmap of front and back side of flashcard
	private static HashMap<String, String> flashcardDic = new HashMap<String, String>();
	
	JButton addButton, doneButton;
	Frame frame;
	JPanel panel;
	TextField frontInput;
	TextField backInput;
	
	
	/**
	 * Creates screen for making flashcards.
	 */
	public void showAll() {
		frame=new Frame();
		panel = new JPanel();
		
		//Header
		JLabel createTitle=new JLabel("CREATE FLASHCARD");
	    createTitle.setFont(new Font("Noto Serif Display", Font.BOLD, 20));
	    createTitle.setAlignmentX(CENTER_ALIGNMENT);
	    
	    //Front of Flashcard
	    JLabel front = new JLabel("Front");
	    front.setAlignmentX(CENTER_ALIGNMENT);
		frontInput=new TextField(30);
	    frontInput.setColumns(20);
	    
	    //Back of Flashcard
	    JLabel back = new JLabel("Back");
	    back.setAlignmentX(CENTER_ALIGNMENT);
		backInput=new TextField(30);
	    backInput.setColumns(20);
	    
	    //Add button for adding new flashcard
	    addButton = new JButton();
	    addButton.setPreferredSize(new Dimension(90, 30));
	    addButton.setText("Add");
	    addButton.setAlignmentX(CENTER_ALIGNMENT);
	    addButton.addActionListener(this);
	    
	    //Done button for changing screens
	    doneButton = new JButton();
	    doneButton.setPreferredSize(new Dimension(90, 30));
	    doneButton.setText("Done");
	    doneButton.setAlignmentX(CENTER_ALIGNMENT);
	    doneButton.addActionListener(this);
	    
	    //Panel layout
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    panel.add(createTitle);
	    panel.add(front);
	    panel.add(frontInput);
	    panel.add(back);
	    panel.add(backInput);
	    panel.add(addButton);
	    panel.add(doneButton);
	    
	    //Add panel to frame
	    frame.add(panel);
	    frame.setVisible(true);
	    
	}

	/**
	 * Action Listener
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Adds text from input fields into hashmap to be used as key-value pairs. 
		if (e.getSource()==addButton) {
		    
			String frontString = frontInput.getText();
			String backString = backInput.getText();
			flashcardDic.put(frontString, backString);
			frontInput.setText("");
			backInput.setText("");
			
			
		}
		
		//Changes screen tp the learning screen
		if (e.getSource()==doneButton) {
			System.out.println(flashcardDic);
			Box box = new Box();
			box.boxOne();
			frame.dispose();
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			
			LearnScreen learnScreen = new LearnScreen();
			learnScreen.showAll();
		}		
	}
	
	/**
	 * Hashmap Getter
	 * @return flashcardDic Hashmap
	 */
	public HashMap<String, String> getHashmap() {
		return flashcardDic;
	}
}