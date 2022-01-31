import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Problem 3
 */
public class MajorityElement {

	public static void main(String[] args) throws FileNotFoundException {
		String fileString = "";
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name of the Majex# text file (without extension): ");
		fileString = in.next();
		
			
		File file = new File("src\\" +fileString+".txt");
		Scanner inputFile = new Scanner(file);	
		
		String data = "", tryAgain;
		
		while(inputFile.hasNext()) {
			data += inputFile.next().trim();
			
		}
		
		int array[] = new int[data.length()];
		
		for(int i = 0; i < data.length(); i++) {
			array[i] = Character.getNumericValue(data.charAt(i));
		}
		
		int input;
		do {
			System.out.print("Enter the method to find the majority element."
					+ "\nEnter 1 for method1 O(N^2), Enter 2 for method2 O(NlogN), Enter 3 for method3 O(N): ");
			input = in.nextInt();
			
			switch(input) {
			
				case 1: System.out.println(method1(array));
						break;
				case 2: System.out.println(method2(array, 0, array.length-1));
						break;
				case 3: System.out.println(method3(array));
						break;
				default: System.out.println("Invalid number entered");
			}
			System.out.print("Would you like to try again? (Y/N) ");
			tryAgain = in.next();
		}while(tryAgain.equalsIgnoreCase("y"));
		
		in.close();
		inputFile.close();
	}

	/**
	 * Method 1, trivial, count the frequency of each element and
	 * check if it is the majority element, O(N^2)
	 * @param array
	 * @return
	 */
	static int method1(int array[]) {
		
		int maxCount = 0, maxCountNum = 0, newMaxCount = 0;
		
		for(int i = 0; i < array.length; i++) {
			
			for(int j = i; j < array.length; j++) {
				
				if(array[i] == array[j]) {
					 maxCountNum = array[j];
					 maxCount++;
				}
			}
			if(maxCount > newMaxCount)
				newMaxCount = maxCount;
			
			if(maxCount > array.length/2)
				break;
			else
				maxCount = 0;
		}
		
		
		if(maxCount > array.length/2) {
			
			return maxCountNum;
		}
		else
			return -1;
	}
	/**
	 * Method 2, divide and conquer, split the array in half repeatedly 
	 * and check for the majority element, repeat with each split, O(NlogN)
	 * of the array
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	static int method2(int array[], int low, int high) {
		
		if(low == high) { // base case, array size = 1
			return array[low];
		}
		int k = (high - low)/2 +low; // split array by the current midpoint
		int elemLeftSub = method2(array, low, k); // recursive call on lower half
		int elemRightSub = method2(array, k+1, high); // recursive call on upper half
		
		if(elemLeftSub == elemRightSub ) { // check if the majority element on each half is the same
			return elemLeftSub;
		}
		// get the frequency of each candidate
		int lCount = getFrequency2(array, elemLeftSub); 
		int rCount = getFrequency2(array, elemRightSub);
		
		// if the frequency is > n/2 it is the majority element, return it
		if(lCount > array.length/2) {
			return elemLeftSub;
		}
		else if(rCount > array.length/2) {
			return elemRightSub;
		}
		else
			return -1;
	}
	
	/**
	 * Method 3, Moore's Voting Algo, scan array to process elements and store 
	 * sequence candidate, the counter is incremented or decremented depending 
	 * on if element being processed is equal to the candidate
	 * @param array
	 * @return
	 */
	static int method3(int array[]) {
		int majIndex = 0, count = 0;
		//scan array
		for(int i = 1; i < array.length-1; i++) {
			// check counter value
			if(count == 0) { 
				majIndex = i;
				count = 1;
			}
			// check if the element being processed is the same as the candidate
			if(array[majIndex] == array[i])
				count++;
			else
				count--;
			
		}
		// check if the frequency is > n/2, if so it's the majority element, return it
		if(getFrequency2(array, array[majIndex]) > (array.length/2))
			return array[majIndex];
		else
			return -1;
	}
	
	/**
	 * Method to check the frequency
	 * @param array
	 * @param x
	 * @return
	 */
	private static int getFrequency2(int array[], int x) {
		int frequency = 0;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == x)
				frequency++;
		}
		return frequency;
	}
	
}
