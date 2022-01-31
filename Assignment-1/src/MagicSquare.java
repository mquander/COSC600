import java.util.Scanner;
/*
 * Problem 2
 */
public class MagicSquare {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		
		String input = "";
		do {
			System.out.print("Enter an odd positive integer n, for the nxn dimensions of the matrix: ");
			int n = in.nextInt();
			
			int check = checkOddNumber(n);
			if(check == -1)
				break;
			
			n = check;
			
			magicSquare(n);
			System.out.print("Would you like to enter another number? (Y/N): ");
			input = in.next();
					
		}while (input.equalsIgnoreCase("y"));
		
		System.out.println("Thank you");
		in.close();
	}
	
	static int checkOddNumber(int n) {
		
		while(n % 2 == 0 || n < 0) {
			System.out.print("The number must be odd and positive to construct the Magic Square, please re-enter or enter -1 to exit: ");
			n = in.nextInt();
			if(n == -1)
				break;
		}
		return n;
	}
	static void magicSquare(int n) {
		int matrix[][] = new int[n][n];
		int i = 0, j = n/2, number = 1;
		
		matrix[0][n/2] = number;
		number++;
		while(number <= n*n) {
			// right move is within the matrix
			if(j + 1 < n){
				
				// upward move not outside of matrix
				if(i - 1 >= 0) {
					
					// square already filled, third condition	
					if(matrix[i-1][j+1] > 0) {
						
						matrix[i+1][j] = number;
						i++; 
					}else { // square not filled
						matrix[i-1][j+1] = number;
						i--;
						j++;
					}
				}else {// upward move outside of matrix
					//first condition
					matrix[n-1][j+1] = number;
					i = n-1;
					j++;
				}
			}
			// right move is outside of matrix, second condition
			else if(j + 1 == n) {
				
				// upward move inside of matrix
				if(i - 1 >= 0) {
					
					// square not filled
					if(matrix[i-1][0] == 0) {
						matrix[i-1][0] = number;
						i-=1;
						j=0;
					}
				}else { // upward move outside of matrix
					matrix[i+1][j] = number; // third condition
					i++;
				}
				
			}
			
		number++;
		}
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++)
				System.out.print(matrix[row][col] + " ");
			System.out.println();	
			
			
		}
	}

}
