/**
 * Course file
 * Course class will help store the students registered 
 * for this particular course in the form of a HashSet.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Course class
 * Course class includes information of a course
 * such as a collection of enrolled students, 
 * capacity of the course, department, course number, and description
 */
public class Course {
    HashSet<Student> enrolled; //a collection of students that are enrolled in this course.
    private final int capacity; 
    private final String department; //This course falls under this department.
    private final String number; //course number
    private final String description; //the description of the course.

    /**
     * Course constructor
     * Course includes information about the course such as
     * department, course number, description, and maximum number of students
     * @param department
     * @param number
     * @param description
     * @param capacity
     */
    public Course(String department, String number, String description, int capacity) {
        if(department == null || number == null || description == null ||
                                            capacity == 0 ){
            throw new IllegalArgumentException();
        }
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.department = department;
        this.number = number;
        this.description = description;
        enrolled = new HashSet<Student>();
    }

    /**
     * enroll(Student student) method
     * Add student to the course
     * @param student
     * @return return true if successfully enrolled and false otherwise.
     */
    public boolean enroll(Student student) {
        //if the course is full
        if(isFull() == true){
            throw new IndexOutOfBoundsException();
        }
        //if student is null
        if(student == null){
            throw new IllegalArgumentException();
        }
        //check if the hashset does not contain the specific student
        if(!enrolled.contains(student)){
            enrolled.add(student);
            return true;
        }
        return false;
    }

    /**
     * uneroll(Student student) boolean method
     * Remove student from the course
     * @param student
     * @return true if successfully removed, and false otherwise
     */
    public boolean unenroll(Student student) {
        if(!enrolled.contains(student)){
            throw new NoSuchElementException();
        }
        if(enrolled.contains(student)){
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * cancel() method
     * Remove all the students from the course if the course is cancelled.
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * isFull() boolean method
     * If the course is at its capacity, return true. 
     * Otherwise, return false.
     * @return return true if the course is full
     */
    public boolean isFull() {
        return enrolled.size() == capacity;
    }

    /**
     * getEnrolledCount() method
     * Return the current number of enrolled students.
     * @return the current number of enrolled students.
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * getAvailableSeats() method
     * Return the number of students that can still enroll in the course
     * @return the number of students can still enroll in the course
     */
    public int getAvailableSeats() {
        int seatRemain = 0;
        if(enrolled.size() < capacity){
            seatRemain = capacity - enrolled.size();
        }
        return seatRemain;
    }

    /**
     * getStudents() method
     * Make a shallow copy of all the students enrolled in this course
     * @return a shallow copy of all the student
     */
    public HashSet<Student> getStudents() {
        return (HashSet<Student>) enrolled.clone();
    }

    /**
     * getRoster() method
     * Turn the collection of enrolled students into an ArrayList
     * by iterating through the list.
     * @return the final ArrayList in the alphabetical order
     */
    public ArrayList<Student> getRoster() {
        
        ArrayList<Student> array = new ArrayList<Student>(enrolled);
        Collections.sort(array);
        return array;
    }

    /**
     * toString() method
     * Return a string representation for a Course object
     * @return a string representation of for Course object
     */
    public String toString() {
        return department + " " + number + " " + "[" + capacity + "]\n" 
                                                            + description;
    }
}
