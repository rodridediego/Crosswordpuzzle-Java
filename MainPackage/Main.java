package MainPackage;
import javax.swing.SwingUtilities;

import Interface.InterfaceComponents;

public class Main 
{
	public static void main(String[] args) 
	{
		String			propertiesFile = "config.properties";
		CrosswordPuzzle	puzzle1 = new CrosswordPuzzle(propertiesFile);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new InterfaceComponents("CrossWord Puzzle " + puzzle1.getFilename(),puzzle1);
			}
		});
	}

}
