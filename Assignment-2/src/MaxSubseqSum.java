import java.util.Random;
import java.util.Scanner;

/**
 * Problem 1
 *
 */
public class MaxSubseqSum {

	static Random random = new Random();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int userChoice;
		String tryAgain="";
		int n, i;
		do {
			System.out.print("Enter the value of n: 5000, 10000, 50000, 100000, or 200000: ");
			n = input.nextInt();
			i = 0;
			int myArray[] = new int[n];
			
			//Fill array with positive and negative random numbers
			while(i < n) {
			
				if(i % 3 == 0)
					myArray[i] = random.nextInt(n) * -1;
				else
					myArray[i] = random.nextInt(n);
				i++;
			}
			int maxSum;
			long time1, time2; // variables to save the System clock values
			
			// prompt user to select which method to use
			System.out.print("Select method to find the Maximum Subsequence Sum (1,2,3,or 4): ");
			
			//execute method and calculate the run time based on the method selected by the user
			userChoice = input.nextInt();
			switch(userChoice) {
			case 1:
				time1 = System.currentTimeMillis();
				maxSum = maxSubseqSum1(myArray);
				time2 = System.currentTimeMillis();
				System.out.println("Maximum Subsequence Sum: " + maxSum);
				System.out.println("Run Time in MilliSeconds: " + (time2-time1));
				break;
			case 2:
				time1 = System.nanoTime();
				maxSum = maxSubseqSum2(myArray);
				time2 = System.nanoTime();
				System.out.println("Maximum Subsequence Sum: " + maxSum);
				System.out.println("Run Time in Nano Seconds: " + (time2-time1));
				break;
			case 3:
				time1 = System.nanoTime();
				maxSum = maxSubseqSum3(myArray);
				time2 = System.nanoTime();
				System.out.println("Maximum Subsequence Sum: " + maxSum);
				System.out.println("Run Time in Nano Seconds: " + (time2-time1));
				break;
			case 4:
				time1 = System.nanoTime();
				maxSum = maxSubseqSum4(myArray);
				time2 = System.nanoTime();
				System.out.println("Maximum Subsequence Sum: " + maxSum);
				System.out.println("Run Time in Nano Seconds: " + (time2-time1));
				break;
			default:
				System.out.print("Error input");
				
			System.out.print("Try another? (Y/N): ");
			tryAgain = input.next();
			}
		}while(tryAgain.equalsIgnoreCase("Y"));
		input.close();
	}
	/*
	 * Method 1
	 */
	public static int maxSubseqSum1(int array[]) {
		int maxSum = 0;
		// loop through the array once for each element
		for(int i = 0; i < array.length; i++) {
			
			for(int j = i; j < array.length; j++) {
				
				int thisSum = 0;
				// sum the elements from the current element to the end
				for(int k = i; k <= j; k++) {
					thisSum += array[k];
				}
				// set maxSum if thisSum is greater
				if(thisSum > maxSum)
					maxSum = thisSum;
			}
		}
		
		return maxSum;
		
	}
	/*
	 * Method 2
	 */
	public static int maxSubseqSum2(int array[]) {
		
		int maxSum = 0;
		// loop through the array for each element
		for(int i = 0; i < array.length; i++) {
			int thisSum = 0;
			
			for(int j = i; j < array.length; j++) {
				// increase thisSum by the current element
				thisSum += array[j];
				
				// set maxSum if thisSum is greater
				if(thisSum > maxSum)
					maxSum = thisSum;
			}
		}
		
		return maxSum;
	}
	/*
	 * Call to recursive method, 3
	 */
	public static int maxSubseqSum3(int array[]) {
		// call the recursive method
		return maxSubseqSumRec3(array, 0, array.length-1) ;
	}
	
	/*
	 * Private recursive method
	 */
	private static int maxSubseqSumRec3(int array[], int left, int right) {
		
		// base case
		if(left == right)
			if(array[left] > 0)
				return array[left];
			else
				return 0;
		
		int mid = (left + right) / 2; // split the array in half
		int maxSumLeft = maxSubseqSumRec3(array, left, mid); // recursive call to the left half
		int maxSumRight = maxSubseqSumRec3(array, mid + 1, right); // recursive call to the right half
		
		int maxLeftBorderSum = 0, leftBorderSum = 0;
		// calculate border sum of left
		for(int i = mid; i >= left; i--) {
			leftBorderSum = array[i];
			
			if(leftBorderSum > maxLeftBorderSum)
				
				maxLeftBorderSum = leftBorderSum;
		}
		
		int maxRightBorderSum = 0, rightBorderSum = 0;
		// calculate border sum of right
		for(int i = mid + 1; i <= right; i++) {
			rightBorderSum = array[i];
			
			if(rightBorderSum > maxRightBorderSum)
				
				maxRightBorderSum = rightBorderSum;
		}
		// return the max of the left, right, or sum of border of left and right
		return Math.max(Math.max(maxSumLeft, maxSumRight), maxRightBorderSum+maxLeftBorderSum);
	}
	
	/*
	 * Method 4
	 */
	public static int maxSubseqSum4(int array[]) {
		
		int maxSum = 0, thisSum = 0;
		// loop through the array once
		for(int i = 0; i < array.length; i++) {
			// increase current sum by the next element
			thisSum += array[i];
			
			// set maxSum if thisSum is greater
			if(thisSum > maxSum)
				maxSum = thisSum;
			// reset thisSum to 0 if current sum drops below 0
			else if(thisSum < 0)
				thisSum = 0;
		}
		
		return maxSum;
	}

}
