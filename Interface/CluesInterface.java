package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainPackage.CrosswordPuzzle;

public class CluesInterface
{//creates a panel with the clues put as JLabel
	private JPanel			panel;
	private List<String[]>	cluesAcrossInterface;
	private List<String[]>	cluesDownInterface;
	
	public CluesInterface(CrosswordPuzzle puzzle)
	{
		int i;
		int j;
		this.cluesAcrossInterface = puzzle.getCluesAcross();
		this.cluesDownInterface = puzzle.getCluesDown();
		this.panel = new JPanel(); 
		this.panel.setPreferredSize(new Dimension(350,600));
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); //create new constrains for the gridbagLayout
		//label for the instructions
		JLabel instructions = new JLabel("Crossword puzzle instructions:");
		instructions.setFont(new Font("Verdana", Font.BOLD, 12));
		instructions.setForeground(Color.MAGENTA);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,0,7,0);
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(instructions,c);
		//label for the word ACROSS
		JLabel across = new JLabel("ACROSS");
		across.setFont(new Font("Verdana", Font.BOLD, 12));
		across.setForeground(Color.MAGENTA);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,0);
		panel.add(across, c);
		//
		j = 0;
		for(String[] y : this.cluesAcrossInterface) //2 nested for loops to go through the clues and create labels to add to the clues panel
		{
			i = 0;
			for(String x: y) 
			{//each clue is divided in 2 strings: one w/ the number of the row/column and the other with the clue itself
				if (i == 0) //in case we are in the number
				{
					JLabel label = new JLabel(x + ". ");// print the number and a string ". " in magenta color
					label.setFont(new Font("Verdana", Font.BOLD, 11));
					label.setForeground(Color.MAGENTA);
					c.gridx = i;
					c.gridy = j + 2;
					c.gridwidth = 1;
					panel.add(label, c);
				}
				else //if it is the clue we crate a label with the clue in plain black
				{
					JLabel label = new JLabel(x);
					label.setFont(new Font("Verdana", Font.PLAIN, 11));
					c.gridx = i;
					c.gridy = j + 2;
					panel.add(label, c);
				}
				i++;
			}
			j++;
		}
		//empty label to create an empty space with the insets
		JLabel white =  new JLabel("");
		c.gridx = 0;
		c.gridy = j+2;
		c.insets = new Insets(0,0,7,0);
		panel.add(white,c);
		//Label for the word DOWN
		JLabel down = new JLabel("DOWN");
		down.setFont(new Font("Verdana", Font.BOLD, 12));
		down.setForeground(Color.MAGENTA);
		c.gridx = 0;
		c.gridy = j + 3;
		c.gridwidth = 10;
		c.insets = new Insets(0,0,0,0);
		panel.add(down, c);
		//same for the down clues than for the across ones
		for(String[] y : this.cluesDownInterface) 
		{
			i = 0;
			for(String x: y)
			{					
				if (i == 0) 
				{
					JLabel label = new JLabel(x + ". ");
					label.setFont(new Font("Verdana", Font.BOLD, 11));
					label.setForeground(Color.MAGENTA);
					c.gridx = i;
					c.gridy = j + 4;
					c.gridwidth = 1;
					panel.add(label, c);
				}
				else 
				{
					JLabel label = new JLabel(x);
					label.setFont(new Font("Verdana", Font.PLAIN, 11));
					c.gridx = i;
					c.gridy = j + 4;
					panel.add(label, c);
				}
				i++;
			}
			j++;
		}

	}
	public JPanel getPanelCluesInterface() 
	{
		return this.panel;
	}

	
}