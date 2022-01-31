import java.util.ArrayList;
import java.util.Scanner;
/*
 * Problem 3
 */
public class JosephusProblem {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of soldiers to delete: "); // get the number of soldiers to start with
		int size = in.nextInt();
		
		ArrayList<String> array = new ArrayList<String>(size); // create an array with the amount of soldiers
		
		System.out.println("Enter the names of the "+size+" soldiers: ");
		
		// populate the array with names from user input
		while(array.size() < size) {
			array.add(in.next());
		}
		
		// get the nth number to delete each time
		System.out.println("Enter a number from 1 to "+size+" to delete every nth name: "); 
		int startIndex = in.nextInt();
		
		// validate the number n input from the user
		while(startIndex < 1 || startIndex > size) {
			System.out.print("The number must be from 1 to "+size+ ", please re-enter: ");
			startIndex = in.nextInt();
		}
		
		// call and print the delete method
		System.out.print(delete(array, startIndex));
		
		in.close();
	}
		
	private static String delete(ArrayList<String> array, int startIndex) {
		
		int i = array.size(); // get the initial size of the array
		int index = startIndex; // get a copy of n, to delete every nth
		startIndex--; // decrement for index purposes
		
		while(i > 1) {
			
			System.out.println("delete: " + array.get((startIndex) % array.size()));
			array.remove(array.get((startIndex) % array.size())); // delete the nth name
			
			// reset the starting index
			startIndex += (index-1); 
			startIndex = startIndex % array.size();
			i--; // decrement the amount of elements to delete
			
		}
		// return the survivor
		return "Survivor: " + array.get(startIndex);
	}
}
