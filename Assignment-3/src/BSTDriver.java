import java.util.Random;

public class BSTDriver {
	
	static BinarySearchTree bst = new BinarySearchTree();
	static Random random = new Random();
	
	public static void main(String[] args) {
		int i = 0;
		
		while(i < 5000) {
			bst.insert(random.nextInt(50000));
			
			i++;
		}
		
		//bst.printTree(bst.root, 0);
		System.out.println("\n\n----------------------");
		
		double sumOfDepthOfAllNodes = BinarySearchTree.sumDepth(bst.root, 0);
	    double avgDepth = sumOfDepthOfAllNodes/bst.size;
				
		//System.out.println("Tree Size: " + bst.size);
		//System.out.println("Sum Depth of All Nodes: " + sumOfDepthOfAllNodes);		
		
		System.out.println("Average Depth: " + avgDepth);
	}

}
