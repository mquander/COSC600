
public class Driver {
	static int array[][] = {{1, 2, 3, 4, 5}, {11, 21, 31, 41, 51}, {12, 22, 32, 42, 52}, {13, 23, 33, 43, 53}, {14, 24, 34, 44, 54}};
	
	public static void main(String[] args) {
		
	System.out.println(search(array, 5, 14));

	}

	 static boolean search(int matrix[][], int n, int value) {
		int i = 0, j = n-1;
		while(i < n && (j > 0 || j == 0)) {
			if(matrix[i][j] == value)
				return true;
			else if(matrix[i][j] > value)
				j--;
			else
				i++;
		}
		return false;
	}
}
