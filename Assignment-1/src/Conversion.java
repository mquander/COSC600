import java.util.Scanner;

/*
 * Problem 1
 */
public class Conversion {
	
	static String bin = "";
	
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
		int decNum; String input = "";
		
		do {
			bin = "";
			System.out.print("Enter a decimal number to convert to binary: ");
			decNum = in.nextInt();
			convert(decNum);
			System.out.println("Binary conversion: " + bin);
			System.out.print("\nWould you like to enter another? (Y/N) ");
			input = in.next();
		}while(input.equalsIgnoreCase("y"));
		
		System.out.println("Thank you");
		in.close();
	}
	
	static void convert(int dec) {
		int r;
		
		if(dec == 0) {
			return;
		}
		else {
			r = dec % 2;
			convert(dec / 2);
		}
		bin += String.valueOf(r);
		
	}
}
