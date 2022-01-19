package Interface;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard 
{
	private JButton					boardBox; //bottom from the grid from which the keyboard is called
	private JFrame					keyFrame;
	private JPanel					keyPanel; 
	private String					displayMessage = "Click one letter";
	private CustomButtonListener	listen = new CustomButtonListener();
	private final String[][]		symbols = {	
			{"A", "Z", "E", "R", "T", "Y", "U", "I", "O", "P"},
			{"Q", "S", "D", "F", "G", "H", "J", "K", "L", "M"},
			{"",  "", "W", "X", "C", "V", "B", "N", "", ""}
			};

	public Keyboard(JButton boxGiven) //2 constructors: 1 when given only a button (display whole keyboard)
	{									// other when given help letter and button (display help letter plus other 4)
		this.boardBox = boxGiven; 
		int i = 0;
		int j = 0;
		this.keyFrame = new JFrame(this.displayMessage);
		this.keyFrame.setPreferredSize(new Dimension(500,300));
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		for(String[] y: this.symbols) 
		{
			i = 0;
			for (String x: y) 
			{
				if(x != "") 
				{
					JButton button = new JButton(x);
					button.addActionListener(listen);
					c.gridx = i;
					c.gridy = j;
					this.keyPanel.add(button, c);
				}
				i++;
			}
			j++;
		}
		this.keyFrame.add(keyPanel);
		this.keyFrame.pack();
		this.keyFrame.setVisible(true);
	}
	public Keyboard(JButton boxGiven, String help) //create object of help keyboard
	{
		// define random different letters and different from help random letters
		String r1 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		while(r1.equals(help)) 
		{
			r1 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		}
		String r2 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		while(r2.equals(r1) || r2.equals(help)) 
		{
			r2 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		}
		String r3 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		while(r3.equals(r1) || r3.equals(r2) || r3.equals(help) ) 
		{
			r3 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		}
		String r4 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		while(r4.equals(r1) || r4.equals(r2) || r4.equals(r3) || r4.equals(help)) 
		{
			r4 = String.valueOf((char)('A'+ Math.floor(Math.random() * 25)));
		}
		//define frame, panel and other variables
		int i = 0;
		int j = 0;
		this.boardBox = boxGiven; 
		this.keyFrame = new JFrame(displayMessage);
		this.keyFrame.setPreferredSize(new Dimension(500,300));
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		//define gridbag layout for the panel to put the keyboard buttons 
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		for(String[] y: this.symbols) 
		{
			i = 0;
			for (String x: y) 
			{
				if(x.equals(help) || x.equals(r1) || x.equals(r2) || x.equals(r3) || x.equals(r4)) //if the letter is one of the help letters or the correct letter create normal button
				{
					JButton button = new JButton(x);
					button.addActionListener(listen);
					c.gridx = i;
					c.gridy = j;
					this.keyPanel.add(button, c);
				}
				else if(x != "")//otherwise, if the letter is one different from the help letters and solution create the button but disable it and set it in gray colour
				{
					JButton button = new JButton(x);
					button.setEnabled(false);
					button.setBackground(Color.GRAY);
					c.gridx = i;
					c.gridy = j;
					this.keyPanel.add(button, c);
				}
				i++;
			}
			j++;
		}
		this.keyFrame.add(keyPanel);
		this.keyFrame.pack();
		this.keyFrame.setVisible(true);
	}

	private class CustomButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{//when clicking one letter, set this letter as text in the button clicked
			String letter = ((JButton) e.getSource()).getText();
			boardBox.setText(letter);
			keyFrame.dispose();
		}
	}
		
}
