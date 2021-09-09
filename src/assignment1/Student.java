package assignment1;

/**
 * The Class Student represents information of a student like name, GPA & RedId.
 */
public class Student {
	
	/** The name. */
	private String name;
	
	/** The gpa. */
	private Float gpa;
	
	/** The redId. */
	private Integer redId;
	
	/**
	 * Instantiates a new student.
	 */
	public Student() {
		super();
	}
	
	/**
	 * Instantiates a new student.
	 *
	 * @param name the name
	 * @param gpa the gpa
	 * @param redId the red id
	 */
	public Student(String name, Float gpa, Integer redId) {
		super();
		this.name = name;
		this.gpa = gpa;
		this.redId = redId;
	}
	
	/**
	 * Compares name of current student with other passed student's name.
	 *
	 * @param otherStudent the other student
	 * @return the integer
	 */
	public Integer compareNames(Student otherStudent) {
		return this.name.compareTo(otherStudent.name);
	}
	
	/**
	 * Prints the redId of the student if he/she on probation.
	 */
	public void printRedIdIfOnProbation() {
		if(this.gpa < 2.85f) {
			System.out.println(this.redId);	
		}
	}
	
	/**
	 * Prints the redId of the student if he/she is a topper.
	 */
	public void printNameIfTopper() {
		if(this.gpa == 4.0f) {
			System.out.println(this.name);	
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Student [name=" + name + ", gpa=" + gpa + ", redId=" + redId + "]";
	}
}
