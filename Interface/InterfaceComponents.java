package Interface;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import MainPackage.CrosswordPuzzle;
//this class include 3 different objects:
//1. Interface for the clues
//2. Interface for the answer
//3. Interface for the grid or board
//the three of them share a common frame and this is the reason why the three of them are implemented as 3 nested classes inside the class InterfaceComponents
//
public class InterfaceComponents //I needed three to have the same frame and thats why I put 3 nested classes inside class components
{
	private JFrame			frame;
	private BoardInterface	board;
	private CluesInterface	clues;
	private AnswerInterface	answer;
	
	public InterfaceComponents(String title,CrosswordPuzzle puzzle) 
	{
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(1000,720));
		board = new BoardInterface(puzzle);
		clues = new CluesInterface(puzzle);
		answer = new AnswerInterface(puzzle);
		frameSettings();
		
	}
	private void frameSettings() 
	{
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints(); 
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 13;
		c1.gridheight = 52;
		c1.anchor = GridBagConstraints.LINE_START;
		frame.add(clues.getPanelCluesInterface(),c1);
		
		c1.gridx = 13;
		c1.gridy = 0;
		c1.gridwidth = 26;
		c1.gridheight = 26;
		c1.anchor = GridBagConstraints.LINE_START;
		c1.fill = GridBagConstraints.NONE;
		frame.add(board.getPanelBoardInterface(),c1);
	
		c1.gridx = 13;
		c1.gridy = 26;
		c1.gridwidth = 3;
		c1.gridheight = 26;
		c1.anchor = GridBagConstraints.LINE_START;
		c1.fill = GridBagConstraints.NONE;
		frame.add(answer.getAnswerPanel(),c1);
		
		this.frame.setMinimumSize(new Dimension(950,720));//set a minumum size so there is no graphic problems with the interface
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
	}
}
