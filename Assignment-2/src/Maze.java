import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/*
 * Problem 2
 */
public class Maze {
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.print("\nImporting the maze.txt file...\n");
		
		// import the file
		File file = new File("src\\maze.txt");
		Scanner inputFile = new Scanner(file);	

		String data = "";
		char matrix[][] = new char[20][20];
		
		// populate the matrix from the file input
		while(inputFile.hasNext()) {
			for(int i = 0; i < matrix.length; i++){
				data = inputFile.next();
				for(int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = data.charAt(j);
				}
			}
		}
		
		// echo print the maze with numbered rows and columns
		printMazeWithRowsCols(matrix);
		
		// prompt user for their starting point
		System.out.print("\nSelect a starting point in the maze, from (0,1) to (19,19),\n"
				+ "the Exit is at (0,0) marked 'E'\n\nEnter the starting row index: ");
		int rowIndex = in.nextInt();
		
		System.out.print("Enter the starting column index: ");
		int colIndex = in.nextInt();
		
		// verify the starting point
		int startCoordinates[] = startPoint(rowIndex, colIndex, matrix);
		
		// find a path to the exit, if possible, from the starting point
		findPath(startCoordinates[0], startCoordinates[1], matrix);
		//printMaze(matrix);
		
		in.close();
		inputFile.close();
	}
	
	private static void printMaze(char[][] matrix) {
		
		for(int i = 0; i < matrix.length; i++) {
			
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void printMazeWithRowsCols(char[][] matrix) {
		
		System.out.print("\n col:    ");
		for(int i = 0; i < matrix.length; i++) {
			if(i > 9)
				System.out.print(i + " ");
			else
				System.out.print(i + "  ");
		}
		System.out.println();
		
		for(int i = 0; i < matrix.length; i++) {
			if(i > 9)
				System.out.print("row "+i+":");
			else
				System.out.print("row  "+i+":");
			
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print( "  " +matrix[i][j]);
			}System.out.println();
		}
	}
	
	private static boolean inBounds(int rowIndex, int colIndex) {
		if((rowIndex >= 0 && rowIndex <= 19) && (colIndex >= 0 && colIndex <= 19)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static int[] startPoint(int rowIndex, int colIndex, char[][] matrix) {
		
		int startCoordinates[] = new int[2];
		
		while(!inBounds(rowIndex, colIndex)) {
			System.out.println("\nPlease choose a starting index between (0,1) and (19,19): \n");
			System.out.print("Enter the starting row index: ");
			rowIndex = in.nextInt();
			System.out.print("Enter the starting column index: ");
			colIndex = in.nextInt();
		}
		
		String startingNum = String.valueOf(matrix[rowIndex][colIndex]);
		
		while((!inBounds(rowIndex, colIndex) || (startingNum).equals("1") || (startingNum).equals("E"))) {
			
			if((startingNum).equals("1") ) {// verify starting point is not a 1 (a hedge)
				startingNum="";
				System.out.println("\nThe starting square can't be a 1, try again: \n");
				System.out.print("Enter the starting row index: ");
				rowIndex = in.nextInt();
				System.out.print("Enter the starting column index: ");
				colIndex = in.nextInt();
				if(inBounds(rowIndex, colIndex))
					startingNum = String.valueOf(matrix[rowIndex][colIndex]);
				
				
			}else if((startingNum).equals("E")){ // verify the starting point is not the exit E
				startingNum="";
				System.out.println("\nPlease choose a starting square other than (0,0): \n");
				System.out.print("Enter the starting row index: ");
				rowIndex = in.nextInt();
				System.out.print("Enter the starting column index: ");
				colIndex = in.nextInt();
				if(inBounds(rowIndex, colIndex))
					startingNum = String.valueOf(matrix[rowIndex][colIndex]);
				
			}else if(!inBounds(rowIndex, colIndex)) { // verify the starting point is within bounds
				
				System.out.println("\nPlease choose a starting index between (0,1) and (19,19): \n");
				System.out.print("Enter the starting row index: ");
				rowIndex = in.nextInt();
				System.out.print("Enter the starting column index: ");
				colIndex = in.nextInt();
				if(inBounds(rowIndex, colIndex))
					startingNum = String.valueOf(matrix[rowIndex][colIndex]);
				
			}
		}
		// when user enters a valid starting point, mark it with 'S'
		matrix[rowIndex][colIndex] = 'S';
		System.out.println();
		// print the complete maze with 'S' marked
		printMaze(matrix);
		
		// set the valid starting coordinates
		startCoordinates[0] = rowIndex;
		startCoordinates[1] = colIndex;
		
		return startCoordinates;
	}
	
	private static void findPath(int rowIndex, int colIndex, char[][] matrix) {
		int i = rowIndex, j = colIndex;
		Stack<Integer> stack = new Stack<Integer>(); // data structure to hold the next move
		int rowCoordinate, colCoordinate;
		System.out.println("\nFinding path from ("+i+","+j+")...");
		char currentChar = matrix[i][j];
		
		while(currentChar != 'E') {
			
				if(j >= 1){ //left move ok
					
					if(matrix[i][j-1]  == '0' || matrix[i][j-1]  == 'E') {
						rowCoordinate = i; colCoordinate = j-1;
						stack.push(rowCoordinate); // push row index
						
						stack.push(colCoordinate); // push col index
						
					}
					
				}
				
				if(j <= 18) { //right move ok
					
					if(matrix[i][j+1] == '0' || matrix[i][j+1]  == 'E') { 
						rowCoordinate = i; colCoordinate = j+1;
						stack.push(rowCoordinate); // push row index
						
						stack.push(colCoordinate); // push col index
						
					}
				}
				
				if(i >= 1) { //up move ok
					
					if(matrix[i-1][j] == '0' || matrix[i-1][j]  == 'E') {
						rowCoordinate = i-1; colCoordinate = j;
						stack.push(rowCoordinate); // push row index
						
						stack.push(colCoordinate); // push col index
						
					}
				}
				
				if(i <= 18) { // down move ok
					
					if(matrix[i+1][j] == '0' || matrix[i+1][j]  == 'E') {
						rowCoordinate = i+1; colCoordinate = j;
						
						stack.push(rowCoordinate); // push row index
						
						stack.push(colCoordinate); // push col index
					}
				}
				
				if(stack.isEmpty()) {
					
					// when the stack is empty and trapped
					matrix[i][j] = '+'; // mark visited
					
					break;
				}else if(j == 0) {
					if((matrix[i][j+1] != '0') && (matrix[i-1][j] != '0' || matrix[i+1][j] != '0')) { // left side trapped condition
						// pop the next coordinates to get the next move
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
						
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
						
					}else {
						
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
						
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
					}
				}else if(i == 19) {
					if((matrix[i][j-1] != '0' || matrix[i][j+1] != '0') && (matrix[i-1][j] != '0')) { //bottom side trapped condition
						// pop the next coordinates to get the next move
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
						
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
						
					}else {
						
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
						
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
					}
				}else if(i == 0) {
					if(j == 19) {
						if((matrix[i][j-1] != '0' && matrix[i+1][j] != '0')) { //top side trapped condition
							// pop the next coordinates to get the next move
							colCoordinate = stack.pop();
							rowCoordinate = stack.pop();
							
							i=rowCoordinate; j=colCoordinate;
							currentChar = matrix[i][j];
							matrix[i][j] = '+'; // mark visited
							
						}else {
							colCoordinate = stack.pop();
							rowCoordinate = stack.pop();
							
							i=rowCoordinate; j=colCoordinate;
							currentChar = matrix[i][j];
							matrix[i][j] = '+'; // mark visited
							
						}
					}else {
						if((matrix[i][j-1] != '0' || matrix[i][j+1] != '0') && (matrix[i+1][j] != '0')) { //top side trapped condition
							// pop the next coordinates to get the next move
							colCoordinate = stack.pop();
							rowCoordinate = stack.pop();
							
							i=rowCoordinate; j=colCoordinate;
							currentChar = matrix[i][j];
							matrix[i][j] = '+'; // mark visited
							
						}else {
							
							colCoordinate = stack.pop();
							rowCoordinate = stack.pop();
							
							i=rowCoordinate; j=colCoordinate;
							currentChar = matrix[i][j];
							matrix[i][j] = '+'; // mark visited
						}
					}
					
				}else if(j == 19){
					if((matrix[i][j-1] != '0') && (matrix[i-1][j] != '0' || matrix[i+1][j] != '0')) { //right side trapped condition
						// pop the next coordinates to get the next move
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
						
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
						
					}else {
						
						colCoordinate = stack.pop();
						rowCoordinate = stack.pop();
												
						i=rowCoordinate; j=colCoordinate;
						currentChar = matrix[i][j];
						matrix[i][j] = '+'; // mark visited
						
					}
					
				}else if((matrix[i][j-1] != '0') && (matrix[i][j+1] != '0') && (matrix[i-1][j] != '0') && (matrix[i+1][j] != '0')) {//internal trapped condition
					// pop the next coordinates to get the next move
					colCoordinate = stack.pop();
					rowCoordinate = stack.pop();
					
					i=rowCoordinate; j=colCoordinate;
					currentChar = matrix[i][j];
					matrix[i][j] = '+'; // mark visited
						
				}else{
					
					colCoordinate = stack.pop();
					rowCoordinate = stack.pop();
										
					i=rowCoordinate; j=colCoordinate;
					currentChar = matrix[i][j];
					matrix[i][j] = '+'; // mark visited
										
				}
			
		}
		System.out.println();
		printMaze(matrix);	
		
		if(currentChar == 'E')
			System.out.println("\nI am free");
		else
			System.out.println("\nHelp, I am trapped");
		
	}
	
}
