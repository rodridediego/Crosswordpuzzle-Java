package MainPackage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.SwingUtilities;

public class CrosswordPuzzle //modificar para que los metodos que modifican variables solo puedan ser llamados desde dentro de esta clase
{//protected para poder acceder desde la clase de interface que va a estar en el mismo paquete|| en verdad podemos acceder por los metodos por lo que pueden ser privadas
	private List<String[]>	board = new ArrayList<String[]>();
	private List<String[]>	cluesDown = new ArrayList<String[]>();
	private List<String[]>	cluesAcross = new ArrayList<String[]>();
	private String			solution = null;
	private String			filename = null;
	private int				rows;
	private int				columns;
	private int				acrossWords;
	private int				downWords;
	
	public CrosswordPuzzle(String propertiesFile) //when creating a puzzle we need to read the config.properties file and the puzzle file.
	{//if there has been any problem when reading them or for instance the filename or the solution are missing, we exit the program displaying the error occurred in the std output
		if(ReadConfig("src/files/"+propertiesFile) == 0) 
		{
			if(ReadPuzzleFile("src/files/" + filename) == 0) 
			{
			
			}
			else 
			{
				System.exit(0);
			}
		}
		else
		{
			System.exit(0);
		}
	}
	
	public void addBoardLine(String Line) 
	{
		String[]	tokens = Line.split(" ");//split the line in boxes of the grid
		this.board.add(tokens);
	}
	
	public void addDownClue(String line) 
	{
		String[]	tokens = line.split("\\. "); //split the clue in the number (tokens[0]) and the clue itself (tokens[1]) as later each one will have different color
		cluesDown.add(tokens); 
		//in this split we need to scape the character "." with the two slashes.
	}
	
	public void addAcrossClue(String line) 
	{
		String[]	tokens = line.split("\\. ");//split the clue in the number (tokens[0]) and the clue itself (tokens[1]) as later each one will have different color
		cluesAcross.add(tokens);
		//in this split we need to scape the character "." with the two slashes.
	}
	
	public void setRowsAndColumns(int r, int c) //we give as input the first line of the puzzle txt to get the numbers of rows and columns
	{
		this.rows = r; 
		this.columns = c;
	}
	public String getFilename() 
	{
		return this.filename;
	}
	public int getRows() 
	{
		return this.rows;
	}
	public int getColumns() 
	{
		return this.columns;
	}
	
	public String getSolution() 
	{
		return this.solution;
	}
	public List<String[]> getBoard()
	{
		return this.board;
	}
	
	public List<String[]> getCluesAcross()
	{
		return cluesAcross;
	}
	
	public List<String[]> getCluesDown()
	{
		return cluesDown;
	}
	
	/*public void printBoard()
	{
		for(String[] x: this.board) 
		{
			for (String y: x) 
			{
				System.out.format("%s",y);
				System.out.format(".");
			}
			System.out.format("\n");
		}
	}*/
	private int ReadConfig(String path) //read config file line by line to assign value to filename and solution.
	{//returns 0 if everything's gone correct or 1 otherwise
		String		line = null;
		String[]	splitLine;
		try 
		{
			Scanner	scan = new Scanner(new FileReader(path));
			while(scan.hasNextLine()) 
			{
				line = scan.nextLine();
				splitLine = line.split("=");//split line in the name of the variable  (splitLine[0]) and its value (splitLine[1])
				if(splitLine[0].equals("filename")) 
				{
					filename = splitLine[1];
				}
				else if(splitLine[0].equals("solution")) 
				{
					solution = splitLine[1].toUpperCase(); //convert the solution to upper case because the puzzle is written in upper case
				}
			}
			scan.close();
			if (filename == null || solution == null ) //if there is no solution or there is not filename we return 1 (indicating something has gone wrong) as it would result in an error later
			{
				System.out.println("ERROR. EITHER THE FILENAME OR THE SOLUTION FOR THE PUZZLE IS MISSING IN THE config.properties FILE");
				return(1);
			}
			return(0);
		}
		catch(FileNotFoundException fnf) 
		{
			System.out.println("ERROR. FILE NOT FOUND WHEN OPENING CONFIG FILE.");
			return(1);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR WHEN READING CONFIG FILE.");
			return(1);
		}
	}
	private int ReadPuzzleFile(String path) //read the file to assign values to the variables of the class puzzle
	{//returns 0 if everything's gone correct or 1 otherwise
		String	line = null;
		int		counter = 0;	
		try 
		{
			Scanner scan = new Scanner(new FileReader(path));
			while(scan.hasNextLine()) 
			{
				if(counter == 0) //In the first line there are contained the numbers of rows and columns of the puzzle
				{
					line = scan.nextLine();
					String[]	tokens = line.split(" ");
					int			r = Integer.parseInt(tokens[0]); 
					int			c = Integer.parseInt(tokens[1]);
					setRowsAndColumns(r,c);//set the rows and columns for this puzzle
				}
				else if (counter < rows + 1) //Here we read and assign value to the board which will be a dynamic matrix in which each element is a box of the board
				{
					line = scan.nextLine();
					addBoardLine(line);
				}
				else//here we assign value to the variables corresponding to the clues of the board
				{
					line = scan.nextLine();
					if(line.equals("ACROSS")) 
					{
						while(scan.hasNextLine() && !line.equals("DOWN")) 
						{
							line = scan.nextLine();
							if (!line.equals("DOWN")) 
							{
								addAcrossClue(line);
							}
							counter++;
						}
						acrossWords = counter - (rows + 2); // save the number of across words
					}
					if(line.equals("DOWN")) 
					{
						while(scan.hasNextLine()) 
						{
							line = scan.nextLine();
							addDownClue(line);
							counter++;
						}
						downWords = counter - (rows + acrossWords + 2); //save the number of down words
					}
				}
				counter++;
			}
			scan.close();
			return(0);
		}
		
		catch(FileNotFoundException fnf) 
		{
			System.out.println("ERROR. FILE NOT FOUND WHEN OPENING PUZZLE FILE.");
			return(1);//if something fails we return 1 as in the method ReadConfig
		}
		
		catch(Exception ex) //if there is no parseable int in the first line, exception need to be handled
		{
			System.out.println("ERROR IN THE FORMAT OF THE PUZZLE FILE.");
			return(1);
		}
	}
	
}
