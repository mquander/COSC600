

public class BinarySearchTree {

	// BinarySearchTree fields
	protected BinaryNode root;
	int size;
	static int COUNT = 5;
	
	// ***************** begin public methods *********************
	public BinarySearchTree() {
		root = null;
	}
	
	// boolean function to check if tree is empty
	public boolean isEmpty() {
		return (root == null);
	}
	
	// boolean function to check if tree contains a node
	public boolean contains(Integer x) {
		return contains(x, root);
	}
		
	// public insert method to call from driver
	public void insert(Integer x) {
		if(contains(x))
			return;
		root = insert(x, root, 0);
		
	}
	
	// recursively print the nodes of the tree
	public void printTree(BinaryNode root, int blank) {
		  
	    if (root == null) 
	        return; 
	    // spacing
	    blank += 5; 
	  
	    // traverse right
	    printTree(root.right, blank); 
	  
	    // print node
	    System.out.print("\n"); 
	    for (int i = 5; i < blank; i++) 
	        System.out.print(" "); 
	    System.out.print(root.element + "\n"); 
	  
	    // traverse left 
	    printTree(root.left, blank); 
		
	}
	// ***************** end public methods ******************
	
	// ***************** begin private methods *******************
	
	// private contains method
	private boolean contains(Integer x, BinaryNode subtree) {
		// base case, return false of node not found
		if(subtree == null)
			return false;
		// compare the node element values of current node and node to check 
		int compareResult = x.compareTo(subtree.element);
		
		if(compareResult < 0)
			return contains(x, subtree.left); // traverse left if less than
		else if(compareResult > 0)
			return contains(x, subtree.right); // traverse right if greater than
		else
			return true;
	}
	
	
	private BinaryNode insert(Integer x, BinaryNode subtree, int depth) {
		// base case, increase tree size and add node
		if(subtree == null) {
			size++;
			return new BinaryNode(x, null, null, depth);
		}
		// compare the node element values of current node and node to check 
		int compareResult = x.compareTo(subtree.element);
		
		if(compareResult < 0) {
			depth++; // increment depth for node
			subtree.left = insert(x, subtree.left, depth);	// traverse left if less than
		}
		else if(compareResult > 0) {
			depth++; // increment depth for node
			subtree.right = insert(x, subtree.right, depth); // traverse right if greater than
		}
		
		return subtree;
	}
	
	// method to calculate sum of depths of all nodes
	static double sumDepth(BinaryNode subtree, int depth) {
		// base case
		if(subtree == null)
			return 0;
		else if(subtree.left == null && subtree.right == null) // return depth of leaf node (no children)
			return depth;
		else {
			return depth + sumDepth(subtree.left, depth + 1) + sumDepth(subtree.right, depth + 1); // traverse left and right to recursively sum depths
		}
	}
	
	// ****************** end private methods **********************
	
	
	// ****************** BEGIN INNER BINARY NODE CLASS ***********************
	class BinaryNode{
		
		// BinaryNode fields
		Integer element;
		BinaryNode left;
		BinaryNode right;
		int depth;
		
		// public constructors to create BinaryNode
		public BinaryNode(Integer element) {
			this(element, null, null);
			depth = 0;
		}
		
		public BinaryNode(Integer element, BinaryNode leftTree, BinaryNode rightTree) {
			this.element = element;
			left = leftTree;
			right = rightTree;
		}
		public BinaryNode(Integer element, BinaryNode leftTree, BinaryNode rightTree, int depth) {
			this.element = element;
			left = leftTree;
			right = rightTree;
			this.depth = depth;
		}
		
		
	}
	// ************************ END INNER BINARY NODE CLASS ***********************
	
}
