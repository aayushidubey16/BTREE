package assignment1;

import java.util.Arrays;

/**
 * The Class Node represents a single node of b-tree containing Students as data 
 * and further children Node as childs if present.
 */
public class Node {
	
	/** The element count. */
	private Integer elementCount;
	
	/** The data. */
	private Student[] data;
	
	/** The childs. */
	private Node[] childs;
	
	/** The is leaf. */
	private Boolean isLeaf;
	
	/** The child count. */
	private Integer childCount;
	
	/**
	 * Instantiates a new node.
	 *
	 */
	public Node() {
		super();
		this.elementCount = 1;
		this.data = new Student[2];
		this.childs = new Node[4];
		this.isLeaf = Boolean.TRUE;
		this.childCount = 0;
	}
	
	/**
	 * Increment element count.
	 */
	public void incrementElementCount() {
		this.elementCount++;
	}
	
	/**
	 * Decrement element count.
	 */
	public void decrementElementCount() {
		this.elementCount--;
	}
	
	/**
	 * Returns the latest initialized student's data index.
	 *
	 * @return the last element index
	 */
	public Integer getLastElementIndex() {
		return this.elementCount - 1;
	}
	
	/**
	 * Gets the array of Node type containing children of current node.
	 *
	 * @return the childs
	 */
	public Node[] getChilds() { 
		return childs; 
	}

	/**
	 * Gets the array of Student type containing data of current node.
	 *
	 * @return the data
	 */
	public Student[] getData() { 
		return data; 
	}
	
	/**
	 * Checks if is leaf node.
	 *
	 * @return the boolean
	 */
	public Boolean isLeafNode() {
		return isLeaf;
	}

	/**
	 * Mark not A leaf node.
	 */
	public void markNotALeaf() {
		this.isLeaf = Boolean.FALSE;
	}
	
	/**
	 * Checks if is current node reached its maximum capacity or not.
	 *
	 * @return the boolean
	 */
	public Boolean isNodeFull() {
		return this.elementCount == 2;
	}
	
	/**
	 * Increment child count.
	 */
	public void incrementChildCount() {
		this.childCount++;
	}
	
	/**
	 * Checks if the current node has child or not.
	 *
	 * @return the boolean
	 */
	public Boolean hasChilds() {
		return this.childCount != 0;
	}
	
	/**
	 * Returns the latest initialized child nodes's index.
	 *
	 * @return the last child index
	 */
	public Integer getLastChildIndex() {
		return this.childCount - 1;
	}
	
	/**
	 * While splitting the internal node this method decrement child count by 2 
	 * as children also got divided between two nodes.
	 */
	public void decrementChildCountBy2() {
		this.childCount = this.childCount - 2;
	}
	
	/**
	 * Traverse b-tree from back to front based on lexicographical order 
	 * and print the name of student who has GPA equals to 4.0.
	 */
	public void traverseReverseInOrderTopperStudent() {
		if (this != null) {
			if (this.hasChilds()) {
				if (this.getLastChildIndex() >= 0)
					this.getChilds()[this.getLastChildIndex()].traverseReverseInOrderTopperStudent();
				for (int i = this.getLastChildIndex() - 1; i >= 0; i--) {
					this.getData()[i].printNameIfTopper();
					this.getChilds()[i].traverseReverseInOrderTopperStudent();
				}
			} else {
				for (int i = this.getLastElementIndex(); i >= 0; i--) {
					this.getData()[i].printNameIfTopper();
				}
			}
		}
	}
	
	/**
	 * Traverse b-tree from front to back based on lexicographical order 
	 * and print the redId of student who is on probation.
	 */
	public void traverseInOrderStudentOnProbation() {
		if (this != null) {
			if (this.hasChilds()) {
				for (int i = 0; i <= this.getLastChildIndex() - 1; i++) {
					this.getChilds()[i].traverseInOrderStudentOnProbation();
					this.getData()[i].printRedIdIfOnProbation();
				}
				if (this.getLastChildIndex() >= 0)
					this.getChilds()[this.getLastChildIndex()].traverseInOrderStudentOnProbation();
			} else {
				for (int i = 0; i <= this.getLastElementIndex(); i++) {
					this.getData()[i].printRedIdIfOnProbation();
				}
			}
		}
	}
	
	/**
	 * Search node in b-tree which is at kth index based on lexicographical order.
	 *
	 * @param counter the counter to keep check of current node index 
	 * @param k the kth index to be searched in b-tree
	 */
	public Integer searchKthElement(Integer counter, Integer k) {
		   if (this != null) {
				if (this.hasChilds()) {
					for (int i = 0; i <= this.getLastChildIndex() - 1; i++) {
						counter = this.getChilds()[i].searchKthElement(counter,k);
						++counter;
						if(counter == k)
						System.out.println(this.getData()[i]);
					}
					if (this.getLastChildIndex() >= 0)
						counter = this.getChilds()[this.getLastChildIndex()].searchKthElement(counter,k);
				} else {
					for(int i = 0 ; i <= this.getLastElementIndex() ; i++) {
						++counter;
						if(counter == k)
						System.out.println(this.getData()[i]);
					}
				}
		   }
		   return counter;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Node [elementCount=" + elementCount + ", data=" + Arrays.toString(data) + ", childs="
				+ Arrays.toString(childs) + ", isLeaf=" + isLeaf + ", childCount=" + childCount + "]";
	}
	
	
}
