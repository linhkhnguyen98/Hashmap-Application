import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;

import org.junit.*;

public class CourseTest {

private Course course1, course2;
private Student student1, student2;

  @Before
  public void setUp() throws Exception {
    // Add additional setup here
    course1 = new Course("CSE", "12", "Data Structures", 200);
    course2 = new Course("ECE", "35", "Circuit", 300);
    student1 = new Student("Linh", "Nguyen", "A1718");
    student2 = new Student("Tony", "Liu", "A1987");
  }

  @Test
  public void testInvalidConstructor() {
    try {
      Course invalidCourse = new Course(null, null, null, 0);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  @Test
  public void testEnrollFullCourse(){
    Course fullCourse = new Course("Math", "1A", "Linear Algebra", 2);
    Student student3 = new Student("Max", "Nguyen", "A2342");
    fullCourse.enroll(student1);
    fullCourse.enroll(student2);
    try{
        fullCourse.enroll(student3);
    }catch(IndexOutOfBoundsException e){
      //good
    }
  }

  @Test
  public void nonExistedStudent(){
    try{
      course1.unenroll(student1);
    }catch(NoSuchElementException e){
      //good
    }
  }
  
  @Test
  public void testnonEmptyCourse(){
    Course fullCourse = new Course("Math", "1A", "Linear Algebra", 2);
    fullCourse.enroll(student1);
    fullCourse.enroll(student2);
    HashSet<Student> copy = fullCourse.getStudents();
    assertEquals(copy, fullCourse.getStudents());
  }

  @Test
  public void testCancel(){
    Course fullCourse = new Course("Math", "1A", "Linear Algebra", 2);
    fullCourse.enroll(student1);
    fullCourse.enroll(student2);
    fullCourse.cancel();
    assertEquals(0, fullCourse.getEnrolledCount());
  }

  
  @Test
  public void testEnrollExistedStudent(){
    course1.enroll(student1);
    course1.enroll(student2);
    assertEquals(2, course1.getEnrolledCount());
  }

  @Test
  public void testNullStudent(){
    try{
      course1.enroll(null);
    }catch(IllegalArgumentException e){
      //success
    }
  }
  @Test
  public void testCourseToString() {
    assertEquals("Check string description of a course", "CSE 12 [200]\nData Structures", course1.toString());
  }

  // ADD YOUR TESTS HERE

}