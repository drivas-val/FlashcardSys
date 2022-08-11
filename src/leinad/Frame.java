package leinad;

import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Create base frame
	Frame(){
		this.setTitle("Flashcard System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(480,200);
		this.setVisible(true);
	}
}