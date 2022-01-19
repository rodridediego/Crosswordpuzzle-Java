package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import MainPackage.CrosswordPuzzle;

public class AnswerInterface
{
	private CustomButtonListener listen = new CustomButtonListener();
	private JButton[] 			answerButton; 
	private JPanel				panel;
	private String				correctSolution;
	private int					solutionLength;
	
	public AnswerInterface(CrosswordPuzzle puzzle) 
	{
		this.correctSolution = puzzle.getSolution();
		this.solutionLength = correctSolution.length(); 
		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints c3 = new GridBagConstraints();
		answerButton = new JButton[solutionLength];
		
		for(int i = 0; i < solutionLength ; i++) //create grid of buttons for the answer
		{
			answerButton[i] = new JButton();
			answerButton[i].setPreferredSize(new Dimension(40,40));
			answerButton[i].setBackground(Color.LIGHT_GRAY);
			answerButton[i].setBorder(new LineBorder(Color.BLACK));
			answerButton[i].addActionListener(listen);
			answerButton[i].setActionCommand("answer");
			c3.gridx = i;
			c3.gridy = 0;
			c3.gridwidth = 1;
			c3.gridheight = 1;
			c3.insets = new Insets(20,0,20,0);
			if(i == 0) 
			{
				c3.insets = new Insets(20,40,20,0);
			}
			panel.add(answerButton[i], c3);
			
		}
		//create the check button
		JButton check = new JButton("CHECK");
		check.addActionListener(listen);
		check.setActionCommand("check");
		c3.gridx = 9;
		c3.gridy = 0;
		c3.gridwidth = 1;
		c3.gridheight = 1;
		c3.insets = new Insets(40,20,40,20);
		this.panel.add(check,c3);
	}
	
	private class CustomButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent eb)//eventBoard 
		{
			JButton	button = (JButton) eb.getSource();	
			String	type = button.getActionCommand();
			String	solutionGiven = "";
			if ((type.equals("check"))) //if the button clicked is the check button we check whether the solution is correct or not
			{
				for(int i = 0; i < solutionLength; i++) //save the solution given in the array solution given
				{
					solutionGiven = solutionGiven + answerButton[i].getText();
				}
				if(solutionGiven.equals(correctSolution)) //if the solution given is the correct, create object checkWindow for the winner
				{//check window needs a boolean argument which indicates whether the answer given is correct or not and display eather the winer or the looser window
					new CheckWindow(true, solutionLength, answerButton);
				}
				else //if the solution is incorrect we create checkwindow giving the value false to the boolean
				{
					new CheckWindow(false, solutionLength, answerButton);//give the checkWindow the solutionLength and answerButton in case retry is clicked
				}//if retry is clicked, clean the answerGrid 
			}	
			else //if the button is not the check button that means it is one of the answer grid so we create a keyboard object to write in it
			{
				new Keyboard(button);  		
			}
			
		}
	}
	public JPanel getAnswerPanel() 
	{
		return this.panel;
	}
}
