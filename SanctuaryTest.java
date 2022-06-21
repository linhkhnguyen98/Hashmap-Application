import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.*;

public class SanctuaryTest {

private Sanctuary sanct1, sanct2, sanct3;

  @Before
  public void setUp() throws Exception {
    sanct1 = new Sanctuary(100, 4);
    sanct2 = new Sanctuary(20, 14);
    sanct3 = new Sanctuary(4, 40);
    // Add additional setup here
  }
  
  @Test
  public void testInvalidConstructor() {
    try {
      Sanctuary invalid = new Sanctuary(-1, 1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  @Test
  public void testGetNumNonExisted(){
    sanct1.rescue("Dog", 10);
    //assertEquals(1, sanct3.rescue("Dog", 5));
    sanct1.rescue("Cat", 10);
    assertEquals(10, sanct1.getNum("Cat"));
    assertEquals(0, sanct1.getNum("Bird"));
    assertEquals(0, sanct1.getNum(null));
  }

  @Test
  public void testRescue(){
    //sanct3.rescue("Dog", 2);
    assertEquals(0, sanct3.rescue("Dog", 2));
    assertEquals(2, sanct3.getNum("Dog"));
    //assertEquals(2, sanct3.getTotal());
    assertEquals(3, sanct3.rescue("Cat", 5));
    assertEquals(2, sanct3.getNum("Dog"));
    assertEquals(2, sanct3.getNum("Cat"));
  }

  @Test
  public void testRelease(){
    sanct3.rescue("Dog", 4);
    sanct3.release("Dog", 2);
    assertEquals(2, sanct3.getNum("Dog"));
  }

  @Test
  public void testReleaseNonExistedAnimal(){
    try{
      sanct3.release("Cat", 2);
    }catch(IllegalArgumentException e){
      //good
    }
  }

  @Test
  public void testReleaseFalseNumber(){
    sanct3.rescue("Dog", 4);
    try{
      sanct3.release("Dog", 5);
    }catch(IllegalArgumentException e){
      //good
    }
  }

  @Test
  public void testGetTotalEmpty() {
    assertEquals("Check total num of animals for new sanctuary", 0, sanct1.getTotal());
  }

  // ADD YOUR TESTS HERE

}