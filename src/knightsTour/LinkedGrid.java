package knightsTour;
public class LinkedGrid {
	// Internal linkedGrid variables
	private Node first; // Variable to keep track of the first node of the linked list
	private int size; // The size of both the x and y axis
	private int solutionCount = 0; // Private integer to keep track of the knight's tour solution count
	
	// Internal methods
	// Random number generation method
	private int rng(int min, int max) {
		return (int)(Math.random()*(max-min+1)+min);
	}
	
	
	// Constructors
	// Base constructor, populate the grid with zeros
	public LinkedGrid(int size) {
		// Revised linked list method
		this.size = size;
		// Create the first node
		Node temp = new Node(0);
		first = temp;
		Node columnMarker = first;
		Node rowMarker = first;
		// Make the first row
		for(int x = 0; x < size-1; x++) {
			// Continue generating new nodes for the row while linking horizontally
			temp = new Node(0);
			temp.setLeft(columnMarker);
			columnMarker.setRight(temp);
			columnMarker = temp;
		}
		// Now, move down and make the next column using a nested for loop
		for(int y = 0; y < size-1; y++) {
			temp = new Node(0);
			columnMarker = temp;
			temp.setUp(rowMarker);
			rowMarker.setDown(temp);
			// Link with the row above
			for(int x = 0; x < size-1; x++) {
				temp = new Node(0);
				temp.setLeft(columnMarker);
				columnMarker.setRight(temp);
				columnMarker.getUp().getRight().setDown(temp);
				temp.setUp(columnMarker.getUp().getRight());
				columnMarker = temp;
				
			}
			// Move the row marker down one row
			rowMarker = rowMarker.getDown();
		}
		// Then, go through the entire linked grid and link in all the paths.
		for(int y = 0; y < size-1; y++) {
			temp = new Node(0);
			columnMarker = temp;
		}
	}
	
	// Method to display the grid
	public void display() {
		// Modified version to work with all linked grid sizes
		Node temp = first; // Temp to mark the current working node
		Node rowMarker = first; // Row marker to mark the first node of each row
		// Nested while loop to ensure it goes down both axes of the grid
		// Continue going down until it has reached the last column
		while(temp != null)
		{
			// Continue going across until the end of the row
			while(temp != null)
			{
				// Different if statements to change the spacing dependent on how large the int is
				if(temp.getData() < 10)
					System.out.print(temp.getData() + "   ");
				else if (temp.getData() < 100)
					System.out.print(temp.getData() + "  ");
				else
					System.out.print(temp.getData() + " ");
				// Go to the next node in the row
				temp = temp.getRight();
			}
			// Go down another line in both the grid and in terminal
			System.out.println();
			rowMarker = rowMarker.getDown();
			// Set the working node as the new row marker.
			temp = rowMarker;
		}
	}
	
	// Method to check if a solution is valid (this is not being used, but may be useful in the future)
	public boolean isValid() {
		Node temp = first; // Temp to mark the current working node
		Node rowMarker = first; // Row marker to mark the first node of each row
		// Nested while loop to ensure it goes down both axes of the grid
		// Continue going down until it has reached the last column
		while(temp != null)
		{
			// Continue going across until the end of the row
			while(temp != null)
			{
				// If any of the nodes are zero, solution is not yet valid
				if(temp.getData() == 0) {
					return false;
				}
				// Go to the next node in the row
				temp = temp.getRight();
			}
			// Go down another line in both the grid
			rowMarker = rowMarker.getDown();
			// Set the working node as the new row marker.
			temp = rowMarker;
		}
		
		// If none of the nodes are invalid (0)
		return true;
	}
	
	// Method to link all paths for knight
	public void linkPaths() {
		Node temp = first; // Temp to mark the current working node
		Node rowMarker = first; // Row marker to mark the first node of each row
		// For each column
		while(temp != null)
		{
			// Continue going across until the end of the row
			while(temp != null)
			{
				/* // Link path by nested if. Not used because the condensed if and statements are much more elegant.
				if(temp.getUp() != null) {
					if(temp.getUp().getUp() != null) {
						if(temp.getUp().getUp().getLeft() != null) {
							temp.setPath1(temp.getUp().getUp().getLeft());
						}
					}
				} */
				
				// Link paths through single if statement with 'and' modifiers for each step.
				// This must be done in order to prevent a null pointer exception.
				// One, link in path1.
				if(temp.getUp() != null && temp.getUp().getUp() != null && temp.getUp().getUp().getLeft() != null) {
					temp.setPath1(temp.getUp().getUp().getLeft());
				}
				// Two, link in path2.
				if(temp.getUp() != null && temp.getUp().getUp() != null && temp.getUp().getUp().getRight() != null) {
					temp.setPath2(temp.getUp().getUp().getRight());
				}
				// Three, link in path3
				if(temp.getUp() != null && temp.getUp().getRight() != null && temp.getUp().getRight().getRight() != null) {
					temp.setPath3(temp.getUp().getRight().getRight());
				}
				// Four, link in path4
				if(temp.getDown() != null && temp.getDown().getRight() != null && temp.getDown().getRight().getRight() != null) {
					temp.setPath4(temp.getDown().getRight().getRight());
				}
				// Five, link in path5
				if(temp.getDown() != null && temp.getDown().getDown() != null && temp.getDown().getDown().getRight() != null) {
					temp.setPath5(temp.getDown().getDown().getRight());
				}
				// Six, link in path6
				if(temp.getDown() != null && temp.getDown().getDown() != null && temp.getDown().getDown().getLeft() != null) {
					temp.setPath6(temp.getDown().getDown().getLeft());
				}
				// Seven, link in path 7
				if(temp.getDown() != null && temp.getDown().getLeft() != null && temp.getDown().getLeft().getLeft() != null) {
					temp.setPath7(temp.getDown().getLeft().getLeft());
				}
				// Eight, link in path 8
				if(temp.getUp() != null && temp.getUp().getLeft() != null && temp.getUp().getLeft().getLeft() != null) {
					temp.setPath8(temp.getUp().getLeft().getLeft());
				}
				temp = temp.getRight();
			}
		
			// Go down another line in both the grid and in terminal
			rowMarker = rowMarker.getDown();
			// Set the working node as the new row marker.
			temp = rowMarker;
		}
	}
	
	
	// Recursive tour method
	public void tour(Node reference, int count) {
		// Set the reference node to the count
		reference.setData(count);
		// So long as a path has not been found
		if(count < size*size) {
			// Begin checking all the paths, if the path is available. Recursively call the method and increase count by 1
			if(reference.getPath1() != null && reference.getPath1().getData() == 0) {
				tour(reference.getPath1(), count+1);
			} // Path 2
			if(reference.getPath2() != null && reference.getPath2().getData() == 0) {
				tour(reference.getPath2(), count+1);
			} // Path 3
			if(reference.getPath3() != null && reference.getPath3().getData() == 0) {
				tour(reference.getPath3(), count+1);
			} // Path 4
			if(reference.getPath4() != null && reference.getPath4().getData() == 0) {
				tour(reference.getPath4(), count+1);
			} // Path 5
			if(reference.getPath5() != null && reference.getPath5().getData() == 0) {
				tour(reference.getPath5(), count+1);
			} // Path 6
			if(reference.getPath6() != null && reference.getPath6().getData() == 0) {
				tour(reference.getPath6(), count+1);
			} // Path 7
			if(reference.getPath7() != null && reference.getPath7().getData() == 0) {
				tour(reference.getPath7(), count+1);
			} // Path 8
			if(reference.getPath8() != null && reference.getPath8().getData() == 0) {
				tour(reference.getPath8(), count+1);
			}
			
			// If none of the paths are available (it got stuck).
			reference.setData(0); // Invalidate the knight's path by setting the data to 0.
		}
		else { // Otherwise, should a potential solution have been found
			// Check if the solution is valid (no nodes are 0)
			// This 'isValid' checker shouldn't be necessary
			//if(isValid()) {
				// If it is valid, display the solution.
				solutionCount++;
				System.out.println("\n>> Solution " + solutionCount + " <<");
				display();
			//}
			
			// Set the reference node to 0.
			reference.setData(0);
		}
	}
	
	
	
	// Method to output the node for any given coordinate set
	public Node find(int x, int y) {
		if(y < size && x < size) {
			Node temp = first;
			// Navigate down
			for(int n = 0; n < y; n++) {
				temp = temp.getDown();
			}
			// Navigate right
			for(int n = 0; n < x; n++) {
				temp = temp.getRight();
			}
			return temp;
		}
		else {
			System.out.println("Cordinates out of grid range.");
			return null;
		}
	}
	
	// Method to populate any given coordinate (not being used for Knight's tour)
	public void populateCordinates(int x, int y, int data) {
		if(y < size && x < size) {
			Node temp = first;
			// Navigate down
			for(int n = 0; n < y; n++) {
				temp = temp.getDown();
			}
			// Navigate right
			for(int n = 0; n < x; n++) {
				temp = temp.getRight();
				}
				temp.setData(data);
			}
			else {
				System.out.println("Cordinates out of grid range.");
			}
	}
		
	// Getters and setters
	public Node getFirst() {
		return first;
	}

	public int getSolutionCount() {
		return solutionCount;
	}

	public void setSolutionCount(int solutionCount) {
		this.solutionCount = solutionCount;
	}

	public void setFirst(Node first) {
		this.first = first;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
}
