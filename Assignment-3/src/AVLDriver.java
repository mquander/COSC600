import java.util.Random;

public class AVLDriver {

	static AVLTree avlTree = new AVLTree();
	static Random random = new Random();
	
	public static void main(String[] args) {
		int i = 0;
		int num;
		
		while(i < 5000) {
			num = random.nextInt(50000);
			avlTree.insert(num);
			
			i++;
		}
		
		//avlTree.printTree(avlTree.root, 0);
		System.out.println("\n\n----------------------");				
		System.out.println("single rotations: "+ avlTree.singleRotations);
		System.out.println("double rotations: "+ avlTree.doubleRotations);

		double sumOfDepthOfAllNodes = avlTree.sumDepth(avlTree.root, 0);
	    double avgDepth = sumOfDepthOfAllNodes/avlTree.size;
	    
	    //System.out.println("Sum Depth of All Nodes: " + sumOfDepthOfAllNodes);
	    System.out.println("Average Depth: " + avgDepth);
		
	}

	
	
}
