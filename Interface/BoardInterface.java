package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import MainPackage.CrosswordPuzzle;

public class BoardInterface 
{
	private List<String[]>			board;
	private JPanel 					panel;
	private JButton[][]				grid;
	private CustomButtonListener	listen = new CustomButtonListener();
	
	public BoardInterface(CrosswordPuzzle puzzle) 
	{
		panel = new JPanel();
		grid = new JButton [puzzle.getRows()][puzzle.getColumns()];
		board = puzzle.getBoard();
		panel.setLayout(new GridBagLayout());
		defineGrid(); //method to define a grid of buttons based on the data extracted from the text file which is contained in the variable board
	}
	public JPanel getPanelBoardInterface() 
	{
		return this.panel;
	}

	private void defineGrid() 
	{
		int i = 0; //counter for the x position of the grid
		int j = 0; //counter for the y position of the grid
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		c2.gridheight = 1;
		c2.gridwidth = 1;
		c2.weightx = 1;
		c2.weighty = 1;
		for(String[] y : this.board) //Use 2 nested for loops to go through the elements of the board
		{
			i = 0;
			for(String x: y) 
			{
				if (x.charAt(0) == 'O') //for the white normal buttons
				{
					grid[j][i] = new JButton();
					grid[j][i].setBackground(Color.WHITE);
					grid[j][i].setBorder(new LineBorder(Color.BLACK));
					grid[j][i].addActionListener(listen);
					grid[j][i].setPreferredSize(new Dimension(40,40));
				}
				else if (x.charAt(0) == 'H') //for the help blue buttons
				{
					grid[j][i] = new JButton();
					grid[j][i].setBackground(Color.CYAN);
					grid[j][i].setBorder(new LineBorder(Color.BLACK));
					grid[j][i].addActionListener(listen);
					grid[j][i].setPreferredSize(new Dimension(40,40));
				}
				else if (x.charAt(0) == 'S') //for the special gray buttons
				{
					grid[j][i] = new JButton();
					grid[j][i].setBackground(Color.GRAY);
					grid[j][i].setBorder(new LineBorder(Color.BLACK));
					grid[j][i].addActionListener(listen);
					grid[j][i].setPreferredSize(new Dimension(40,40));
				}
				else if (x.charAt(0) == 'X') //for the black buttons
				{
					grid[j][i] = new JButton();
					grid[j][i].setBackground(Color.BLACK);
					grid[j][i].setBorder(new LineBorder(Color.BLACK));
					grid[j][i].setEnabled(false); //so the black buttons don't have functionality
					grid[j][i].setPreferredSize(new Dimension(40,40));
				}
				else 
				{
					System.out.println("INCORRECT FORMAT OF INPUT FILE");
					System.exit(1);
				}
				c2.gridx = i;
				c2.gridy = j;
				grid[j][i].setActionCommand(x);
				panel.add(grid[j][i], c2); //add each button to the panel
				i++;
			}
			j++;
		}
		panel.setBorder(BorderFactory.createEmptyBorder(50,5,5,5));
	}
	private class CustomButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent eb)
		{
			JButton	button = (JButton) eb.getSource();//get the button in which the click has done
			String	type = button.getActionCommand();
			if (type.charAt(0) != 'H') //if it is not an special button generate normal keyboard
			{
				new Keyboard(button);		
			}
			else //if it is a special button generate help keyboard (use constructor with second argument)
			{ // This second argument is a string with the solution for this button
				new Keyboard(button, type.substring(2,3)); 
			}
		}	
	}
}
