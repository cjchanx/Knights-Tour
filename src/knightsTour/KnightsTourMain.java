package knightsTour;

import java.util.Scanner;

public class KnightsTourMain {
	
	// Method for taking error trapped inputs
	public static int input(int min, int max) {
		Scanner input = new Scanner(System.in);
		boolean inputOkay = true;
		int userInput = 0;
		if(min > max) { // Reverses the min and max
			int tempMin = min; // Track min, temporarily
			min = max; // Set the minimum to the maximum
			max = tempMin; // Set the max to the previous temporary min
		}
		do {
			inputOkay = true;
			try { // Try getting an input
				userInput = input.nextInt();
			} catch(Exception e) { // Catch the exception if there is one
				String garbage = input.nextLine();
				System.out.print("That is not a valid input. Enter again: ");
				inputOkay = false;
			}
			
			if(inputOkay != false && userInput < min || userInput > max) { // Check that the input is within the range
				System.out.print("That is not a valid input. Enter again: ");
				inputOkay = false;
			}
		}while(inputOkay != true);
		
		return userInput; // Return the VALID input
	}
	
	// Main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Main Knights Tour Menu
		int restart = 0;
		
		// Main meno do-while loop
		do {
			// Menu
			System.out.println("\t>> KNIGHTS TOUR <<");
			System.out.println(" >> What board size would you like to use?");
			System.out.print("Enter Board Size (max 100): ");
			int boardSize = input(0,100);
			System.out.println(" >> Which point would you like to start from (x,y) where 0,0 is first space?");
			System.out.print("Enter starting point x coordinate: ");
			int posX = input(0,boardSize-1);
			System.out.print("Enter starting point y coordinate: ");
			int posY = input(0, boardSize-1);
			// Tell the user that the program is running (finding solutions for some board sizes can take a VERY long time)
			System.out.println("\t>> Running program...");
			
			// Run knight's tour
			// Generate a linked grid of the specified size
			LinkedGrid LG = new LinkedGrid(boardSize);
			// Link all the paths in the grid
			LG.linkPaths(); 
			// Get the start time
			long startTime = System.currentTimeMillis();
			
			// Call the tour method
			LG.tour(LG.find(posX, posY), 1);
			
			// Check if there were any solutions printed
			if(LG.getSolutionCount() == 0) {
				// If not, inform the user that that combination of size and starting point has no solutions
				System.out.println("That combination of board size and starting point has no solutions.");
			}
			else {
				// Otherwise, inform the user of the result
				System.out.println("\n\t>> RESULT <<");
				System.out.println("Knight's Tour Complete.");
				System.out.println("Board Size          :\t" + boardSize);
				System.out.println("Starting Point      :\t" + posX + "," + posY + "");
				System.out.println("Number of Solutions :\t" + LG.getSolutionCount());
				System.out.println("Program Time        :\t" + (System.currentTimeMillis()-startTime) + "ms.");
			}
			
			// Ask the user if they would like to restart
			System.out.println("\n >> Restart Program? (1 = yes, 0 = no)");
			System.out.print("Enter Selection: ");
			restart = input(0,1);
			
			// Skip a few lines forward in console (for appearance)
			System.out.println("\n\n\n");
		}while(restart == 1);
		
		// Inform the user that the program has been terminated.
		System.out.println("Program Terminated.");
		
	}

}
