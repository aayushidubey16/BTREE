package assignment1;

/**
 * The Class BTree represents collection of node and node count as a b-tree with order 3.
 */
public class BTree {

	/** The root. */
	private Node root;
	
	/** The student count. */
	private Integer studentCount;
	
	/**
	 * Insert the student object to the b-tree.
	 *
	 * @param studentToInsert the Student to insert into b-tree
	 */
	public void insert(Student studentToInsert){
		//If b-tree is empty this if block will create new node and assign the student data to it.
		if(this.root == null) {
			this.root = new Node();
			this.studentCount = 1;
			this.root.getData()[0] = studentToInsert;
		} else { //If b-tree is already initialized then in this else block based on node status assignment happens
			this.studentCount++; //incrementing student count to keep track of inserted students
			// If root node is a leaf node then assign the student data to it.
			if(this.root.isLeafNode()) {
				//If root node is full then need to split it and create new root node.
				if(this.root.isNodeFull()) {
					int compare1 = this.root.getData()[0].compareNames(studentToInsert);
					int compare2 = this.root.getData()[1].compareNames(studentToInsert);
					Node newNode1 = null;
					Node newNode2 = null;
					if(compare1 < 0) { // if student to insert has higher value
						newNode1 = new Node();
						newNode2 = new Node();
						newNode1.getData()[0] = this.root.getData()[0];
						newNode2.getData()[0] = studentToInsert;
						this.root.getData()[0] = this.root.getData()[1];
					} else if(compare1 > 0 && compare2 < 0) { // if student to insert has mid value
						newNode1 = new Node();
						newNode2 = new Node();
						newNode1.getData()[0] = this.root.getData()[0];
						newNode2.getData()[0] = this.root.getData()[1];
						this.root.getData()[0] = studentToInsert;
					} else { // if student to insert has lowest value
						newNode1 = new Node();
						newNode2 = new Node();
						newNode1.getData()[0] = studentToInsert;
						newNode2.getData()[0] = this.root.getData()[1];
						this.root.getData()[0] = this.root.getData()[0];
					}
					this.root.getData()[1] = null;
					this.root.markNotALeaf();
					this.root.getChilds()[0] = newNode1;
					this.root.incrementChildCount();
					this.root.getChilds()[1] = newNode2;
					this.root.incrementChildCount();
					this.root.decrementElementCount();
				} else {// Need to check lexicographical order and insert the node if root node is not full
					int compare = this.root.getData()[0].compareNames(studentToInsert);
					if(compare > 0) {
						this.root.getData()[1] = this.root.getData()[0];
						this.root.getData()[0] = studentToInsert;
					} else {
						this.root.getData()[1] = studentToInsert;
					}
					this.root.incrementElementCount();
				}
			} else {// If root node is not a leaf node then assign the student data to it.
				insertToOtherNode(studentToInsert, this.root, null, 0);
			}
		}
	}
	
	/**
	 * Insert the student object to other nodes of the b-tree.
	 *
	 * @param studentToInsert the Student to insert into b-tree
	 * @param currentNode the current node which is currently on stack based on recursion 
	 * @param parentNode the parent node of current node
	 * @param currentNodeIndex the current node index based on childs array
	 * @return the student which is overflow based on node splitting or null if not
	 */
	private Student insertToOtherNode(Student studentToInsert, Node currentNode, Node parentNode, Integer currentNodeIndex) {
		Student overFlownEntry = null;
		// If node is a leaf node then assign the student data to it.
		if(currentNode.isLeafNode()) {
			//If node is full then need to split it into two and assign overflow student of reference.
			if(currentNode.isNodeFull()) {
				int compare1 = currentNode.getData()[1].compareNames(studentToInsert);
				int compare2 = currentNode.getData()[0].compareNames(studentToInsert);
				Node newNode1 = currentNode;
				Node newNode2 = new Node();
				if(compare1 < 0) {
					newNode2.getData()[0] = studentToInsert;
					overFlownEntry = currentNode.getData()[1];
				} else if(compare1 > 0 && compare2 < 0) {
					newNode2.getData()[0] = currentNode.getData()[1];
					overFlownEntry = studentToInsert;
				} else {
					newNode1.getData()[0] = studentToInsert;
					newNode2.getData()[0] = currentNode.getData()[1];
					overFlownEntry = currentNode.getData()[0];
				}
				newNode1.getData()[1] = null;
				newNode1.decrementElementCount();
				for(int index = currentNodeIndex+1 ; index <= parentNode.getLastChildIndex() ; index++) {
					parentNode.getChilds()[index+1] = parentNode.getChilds()[index];
				}
				parentNode.getChilds()[currentNodeIndex] = newNode1;
				parentNode.getChilds()[currentNodeIndex+1] = newNode2;
				parentNode.incrementChildCount();
			} else {// Need to check lexicographical order and insert the node and assign null to overflow reference
				int compare = currentNode.getData()[0].compareNames(studentToInsert);
				if(compare > 0) {
					currentNode.getData()[1] = currentNode.getData()[0];
					currentNode.getData()[0] = studentToInsert;
				} else {
					currentNode.getData()[1] = studentToInsert;
				}
				currentNode.incrementElementCount();
			}
		} else {// If node is not a leaf node then assign the student data to it.
			if(currentNode.isNodeFull()) {
				int compare1 = currentNode.getData()[1].compareNames(studentToInsert);
				int compare2 = currentNode.getData()[0].compareNames(studentToInsert);
				int childIndex = 0;
				if(compare1 < 0) {
					childIndex = 2;
				} else if(compare1 > 0 && compare2 < 0) {
					childIndex = 1;
				} 
				Student newOverFlownEntry = insertToOtherNode(studentToInsert, currentNode.getChilds()[childIndex], currentNode, childIndex);
				if(newOverFlownEntry != null ) {
					Node newNode1 = currentNode;
					Node newNode2 = new Node();
					newNode2.markNotALeaf();
					if(compare1 < 0) {
						newNode2.getData()[0] = newOverFlownEntry;
						overFlownEntry = currentNode.getData()[1];
					} else if(compare1 > 0 && compare2 < 0) {
						newNode2.getData()[0] = currentNode.getData()[1];
						overFlownEntry = newOverFlownEntry;
					} else {
						newNode1.getData()[0] = newOverFlownEntry;
						newNode2.getData()[0] = currentNode.getData()[1];
						overFlownEntry = currentNode.getData()[0];
					}
					newNode1.getData()[1] = null;
					newNode1.decrementElementCount();
					newNode2.getChilds()[0] = newNode1.getChilds()[2];
					newNode2.incrementChildCount();
					newNode2.getChilds()[1] = newNode1.getChilds()[3];
					newNode2.incrementChildCount();
					newNode1.getChilds()[2] = null;
					newNode1.getChilds()[3] = null;
					newNode1.decrementChildCountBy2();
					if (parentNode != null) {
						for (int index = currentNodeIndex + 1; index < parentNode.getLastChildIndex(); index++) {
							parentNode.getChilds()[index + 1] = parentNode.getChilds()[index];
						}
						parentNode.getChilds()[currentNodeIndex] = newNode1;
						parentNode.getChilds()[currentNodeIndex + 1] = newNode2;
						parentNode.incrementChildCount();
					} else { // while splitting if we reached root node and that node is also overflowing then split it into two and make new root node
						Node newRootNode = new Node();
						newRootNode.markNotALeaf();
						newRootNode.incrementChildCount();
						newRootNode.incrementChildCount();
						newRootNode.getChilds()[currentNodeIndex] = newNode1;
						newRootNode.getChilds()[currentNodeIndex + 1] = newNode2;
						newRootNode.getData()[0] = overFlownEntry;
						overFlownEntry = null;
						this.root = newRootNode;
					}
				}
			} else {// Need to check lexicographical order and insert the node
				int compare = currentNode.getData()[0].compareNames(studentToInsert);
				int childIndex = 0;
				if(compare < 0) {
					childIndex = 1;
				} 
				Student newOverFlownEntry = insertToOtherNode(studentToInsert, currentNode.getChilds()[childIndex], currentNode, childIndex);
				if (newOverFlownEntry != null) {
					if (childIndex == 1) {
						currentNode.getData()[1] = newOverFlownEntry;
					} else if (childIndex == 0) {
						currentNode.getData()[1] = currentNode.getData()[0];
						currentNode.getData()[0] = newOverFlownEntry;
					}
					currentNode.incrementElementCount();
				}
			}
		}
		return overFlownEntry;
	}
	
	/**
	 * Search node in b-tree which is at kth index based on lexicographical order.
	 *
	 * @param k the kth index to be searched in b-tree
	 */
	public void searchKthElement(Integer k) throws Exception {
		if(k <= this.studentCount) {
			this.root.searchKthElement(0, k);
		} else {
			throw new Exception("Index out-of-bound for " + k + 
					"th element search because b-tree contains only " + studentCount + " elements");
		}
	}
	
	/**
	 * Traverse b-tree from front to back based on lexicographical order 
	 * and print the redId of student who is on probation.
	 */
	public void traverseInOrderStudentOnProbation() {
		this.root.traverseInOrderStudentOnProbation();
	}
	 
	/**
	 * Traverse b-tree from back to front based on lexicographical order 
	 * and print the name of student who has GPA equals to 4.0.
	 */
	public void traverseReverseInOrderTopperStudent() {
		this.root.traverseReverseInOrderTopperStudent();
	}
	
	/**
	 * Prints the detail of b-tree.
	 */
	public void print() {
		System.out.println(this.toString());
	}
	 
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BTree [root=" + root + ", nodeCount=" + studentCount + "]";
	}

	
	
}
