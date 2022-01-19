package Interface;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CheckWindow 
{
	private JFrame frame;
	private JPanel panel;
	private JLabel message;
	private JButton ok;
	private JButton tryAgain;
	private JButton close;
	private JButton[] answerButton;
	private int solutionLength;
	private CustomButtonListener listen = new CustomButtonListener();
	
	public CheckWindow(boolean correct, int length, JButton[] button) //correct = true means correct solution--> winner 
	{//we give as arguments the length of the answerGrid and the answerButton in case we want to retry and empty the previous attempt when we fail
		this.solutionLength = length;
		this.answerButton = button;
		if (correct == true) 
		{
			successWindow();//window to display when the answer is correct
		}
		else 
		{
			looserWindow(); //window to display when the answer is incorrect
		}
	}
	private void successWindow() 
	{
		this.frame = new JFrame("CONGRATULATIONS!!!");
		this.panel = new JPanel();
		this.message = new JLabel("CORRECT ANSWER");
		this.message.setFont(new Font("Verdana", Font.BOLD, 22));
		this.message.setForeground(Color.GREEN);
		this.ok = new JButton("OK");
		this.ok.addActionListener(listen);
		this.close = new JButton("Close");
		this.close.addActionListener(listen);
		
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints c4 = new GridBagConstraints();
		
		c4.gridx = 0;
		c4.gridy = 0;
		c4.gridheight = 1;
		c4.gridwidth = 3;
		c4.anchor = GridBagConstraints.CENTER;
		c4.insets = new Insets(15,30,15,30);
		this.panel.add(message,c4);
		
		c4.gridx = 0;
		c4.gridy = 1;
		c4.gridheight = 1;
		c4.gridwidth = 1;
		c4.insets = new Insets(20,60,20,25);
		this.panel.add(ok,c4);
		
		c4.gridx = 2;
		c4.gridy = 1;
		c4.gridheight = 1;
		c4.gridwidth = 1;
		c4.insets = new Insets(20,25,20,50);
		this.panel.add(close,c4);
		
		this.frame.add(panel);
		this.frame.pack();
		this.frame.setVisible(true);
	
	}
	private void looserWindow() 
	{
		this.frame = new JFrame("KEEP TRYING");
		this.panel = new JPanel();
		this.message = new JLabel("WRONG ANSWER");
		this.message.setFont(new Font("Verdana", Font.BOLD, 22));
		this.message.setForeground(Color.RED);
		this.tryAgain = new JButton("Try again");
		this.tryAgain.addActionListener(listen);
		this.close = new JButton("Close");
		this.close.addActionListener(listen);
		
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints c4 = new GridBagConstraints();
		
		c4.gridx = 0;
		c4.gridy = 0;
		c4.gridheight = 1;
		c4.gridwidth = 3;
		c4.anchor = GridBagConstraints.CENTER;
		c4.insets = new Insets(15,30,15,30);
		this.panel.add(message,c4);
		
		c4.gridx = 0;
		c4.gridy = 1;
		c4.gridheight = 1;
		c4.gridwidth = 1;
		c4.insets = new Insets(20,60,20,25);
		this.panel.add(tryAgain,c4);
		
		c4.gridx = 2;
		c4.gridy = 1;
		c4.gridheight = 1;
		c4.gridwidth = 1;
		c4.insets = new Insets(20,25,20,62);
		this.panel.add(close,c4);
		
		this.frame.add(panel);
		this.frame.pack();
		this.frame.setVisible(true);
	
	}
	private class CustomButtonListener implements ActionListener //que pasa al pulsar un botón
	{//modificar para en función de que si se pincha H,S U O haga una cosa u otra

		@Override
		public void actionPerformed(ActionEvent eb)//eventBoard 
		{
			JButton button = (JButton) eb.getSource();
			if (button == ok) //when clicking ok in the winner window, dispose the winner window
			{
				frame.dispose();
			}
			else if (button == close) //when clicking close, exit the program
			{
				System.exit(0);
			}
			else if (button == tryAgain) //when clicking try again, empty the answerGrid
			{
				for(int i = 0; i < solutionLength; i++) 
				{
					answerButton[i].setText("");
				}
				frame.dispose();
			}
			
		}

	}
}
//ver si se puede borrar lo de los botones al comprobar la solución, sino se puede, convertir el boton de ok y try again en uno solo
