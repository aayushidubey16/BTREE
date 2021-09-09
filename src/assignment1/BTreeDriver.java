package assignment1;

/**
 * The Class BTreeDriver.
 */
public class BTreeDriver {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		
		/*
		 *  1. Implements a B-tree with order 3. 
			2. Each element in the B-tree contains a Student object. A Student has a name, red id and a GPA 
			3. You can add elements to a B-tree. When you add a new Student object to the list is maintained in lexicographical order by student’s name. 
		 */
		BTree bTree = new BTree();
		bTree.insert(new Student("A",4.0f,825874450));
		bTree.insert(new Student("B",1.0f,825874451));
		bTree.insert(new Student("C",2.0f,825874452));
		bTree.insert(new Student("D",4.0f,825874453));
		bTree.insert(new Student("E",4.0f,825874454));
		bTree.insert(new Student("F",2.5f,825874455));
		bTree.insert(new Student("G",3.0f,825874456));
		bTree.insert(new Student("H",3.0f,825874457));
		bTree.insert(new Student("I",2.1f,825874458));
		bTree.insert(new Student("J",1.5f,825874459));
		bTree.insert(new Student("Aa",4.0f,825874460));
		bTree.insert(new Student("Cc",4.0f,825874461));
		bTree.insert(new Student("K",4.0f,825874462));
		bTree.insert(new Student("G",4.0f,825874463));
		bTree.insert(new Student("H",4.0f,825874464));
		bTree.insert(new Student("Ee",4.0f,825874465));
		bTree.insert(new Student("Ee",4.0f,825874466));
		bTree.print();
		/*
		 * 4. Given a k, your code returns the k'th element in the B-tree in lexicographical order. If k is out-of-bounds throw an exception. 
		*/
		bTree.searchKthElement(17);
		/*
		 * 5. Print out the Red Ids of the students that are on probation (GPA less than 2.85) that in the list from the front to the back of the list.
		*/
		bTree.traverseInOrderStudentOnProbation();
		/*
		 * 6. Print out the names of the students with GPA of 4.0 in the list from the back to the front of the list.
		*/
		bTree.traverseReverseInOrderTopperStudent();
	}

}
