

public class AVLTree{
	
	AVLNode root;
	int size;
	int singleRotations;
	int doubleRotations;
	static int COUNT = 5;
	
	public AVLTree() {
		root = null;
		singleRotations = 0;
		doubleRotations = 0;
	}
	// boolean method to check if tree is empty	
	public boolean isEmpty() {
		return (root == null);
	}
	// public insert method
	public void insert(Integer x) {
		if(contains(x))
			return;
		root = insert(x, root, 0);
	}
	// public contains method
	public boolean contains(Integer x) {
		return contains(x, root);
	}
	
	// method to search if tree contains a certain node
	protected boolean contains(Integer x, AVLNode subtree) {
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
	
	// method to calculate sum of depths of all nodes
	double sumDepth(AVLNode subtree, int depth) {
		// base case
		if(subtree == null)
			return 0;
		else if(subtree.left == null && subtree.right == null) // return depth of leaf node (no children)
			return depth;
		else {
			return depth + sumDepth(subtree.left, depth + 1) + sumDepth(subtree.right, depth + 1); // traverse left and right to recursively sum depths
		}
	}

	// method to return node's height
	public int height(AVLNode subtree) {
		
		if(subtree == null) //  empty subtree
			return -1;
		else
			return subtree.height; // return nodes height
	}
	
	// ****************** begin private AVL Tree methods ********************
	// method to insert node
	private AVLNode insert(Integer x, AVLNode subtree, int depth) {
		// base case, increase tree size and add node
		if(subtree == null) {
			size++;
			return new AVLNode(x, null, null, depth);
		}			
		// compare the node element values of current node and node to check 
		int compareResult = x.compareTo(subtree.element);
		
		if(compareResult < 0) {
			depth++; // increment depth for node
			subtree.left = insert(x, subtree.left, depth); // traverse left if less than
		}		
		else if(compareResult > 0) {
			depth++; // increment depth for node
			subtree.right = insert(x, subtree.right, depth); // traverse right if greater than
		}
		
		return balance(subtree);
	}
	
	// method to balance the tree 
	private AVLNode balance(AVLNode subtree) {
		if(subtree == null)
			return subtree;
		// balance if left subtree height greater than right subtree height
		if(height(subtree.left) - height(subtree.right) > 1) {
			// rotate based on which subtree's subtree has greater height
			if(height(subtree.left.left) >= height(subtree.left.right)) {
				subtree = rotateWithLeftChild(subtree);
				singleRotations++; // increment single rotations
			}
			else {
				subtree = doubleWithLeftChild(subtree);
				doubleRotations++; // increment double rotations
			}
		}
		// balance if right subtree height greater than left subtree height
		else if(height(subtree.right) - height(subtree.left) > 1) {
			// rotate based on which subtree's subtree has greater height
			if(height(subtree.right.right) >= height(subtree.right.left)) {
				subtree = rotateWithRightChild(subtree);
				singleRotations++; // increment single rotations
			}
			else {
				subtree = doubleWithRightChild(subtree);
				doubleRotations++; // increment double rotations
			}
		}
		// update current root height
		subtree.height = Math.max(height(subtree.left), height(subtree.right)) + 1;
		return subtree;
	}
	
	private AVLNode rotateWithLeftChild(AVLNode subtreeroot) {
		
		AVLNode rootLeftChild = subtreeroot.left;
		// assign left child's right child to subtree root's left child
		subtreeroot.left = rootLeftChild.right; 
		// assign left child's right to be the subtree root 
		rootLeftChild.right = subtreeroot; 
		// assign height as max height of subtrees
		subtreeroot.height = Math.max(height(subtreeroot.left), height(subtreeroot.right)) + 1; 
		// assign height as max height of subtrees
		rootLeftChild.height = Math.max(height(rootLeftChild.left), subtreeroot.height) + 1; 
		
		
		return rootLeftChild;
	}
	
	private AVLNode rotateWithRightChild(AVLNode subtreeroot) {
		
		AVLNode rootRightChild = subtreeroot.right;
		// assign right child's left to be subtree root's right child
		subtreeroot.right = rootRightChild.left; 
		// assign root's right child to be the subtree root 
		rootRightChild.left = subtreeroot; 
		// assign height as max height of subtrees
		subtreeroot.height = Math.max(height(subtreeroot.left), height(subtreeroot.right)) + 1; 
		// assign height as max height of subtrees
		rootRightChild.height = Math.max(height(rootRightChild.right), subtreeroot.height) + 1; 
		
		
		return rootRightChild;
	}
	
	private AVLNode doubleWithLeftChild(AVLNode node3) {
		// single rotation with the right child
		node3.left = rotateWithRightChild(node3.left); 
		// single rotation with the left child
		return rotateWithLeftChild(node3); 
	}
	
	private AVLNode doubleWithRightChild(AVLNode node1) {
		// single rotation with the left child
		node1.right = rotateWithLeftChild(node1.right); 
		// single rotation with the right child
		return rotateWithRightChild(node1); 
		
	}
		
	 // *************** end private AVL methods ***********************
	
	
	// recursively print the nodes of the tree
		public void printTree(AVLNode root, int blank) {
			  
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
	
	// ****************** BEGIN INNER AVL NODE CLASS **********************
	class AVLNode{
		// AVLNode fields
		int depth;
		int height;
		Integer element;
		AVLNode left, right;
		
		// public constructors to create BinaryNode
		public AVLNode(Integer element) {
			this(element, null, null);
			height = 0;
		}
		
		public AVLNode(Integer element, AVLNode leftTree, AVLNode rightTree) {
			this.element = element;
			this.left = leftTree;
			this.right = rightTree;
			this.height = 0;
		}
		
		public AVLNode(Integer element, AVLNode leftTree, AVLNode rightTree, int depth) {
			this.element = element;
			this.left = leftTree;
			this.right = rightTree;
			//this.height = height;
			this.depth = depth;
		}
	}
	// ***************** END INNER AVL NODE CLASS********************
}
