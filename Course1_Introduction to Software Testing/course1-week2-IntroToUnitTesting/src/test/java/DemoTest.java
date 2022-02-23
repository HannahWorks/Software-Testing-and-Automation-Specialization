import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */

public class DemoTest {

    @Test
    public void Test_MainProgram1(){
        ByteArrayInputStream in = new ByteArrayInputStream("5\n9\n13\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {};
        Demo.main(args);

        String consoleOutput = "Enter side 1: \n";
        consoleOutput += "Enter side 2: \n";
        consoleOutput += "Enter side 3: \n";
        consoleOutput += "This is a triangle.\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    public void Test_MainProgram2(){
        ByteArrayInputStream in = new ByteArrayInputStream("5\n7\n13\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {};
        Demo.main(args);

        String consoleOutput = "Enter side 1: \n";
        consoleOutput += "Enter side 2: \n";
        consoleOutput += "Enter side 3: \n";
        consoleOutput += "This is not a triangle.\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    public void test_isTriangle1() {
        assertTrue(Demo.isTriangle(5,9,13));
    }

    @Test
    public void test_isTriangle2() {
        assertTrue(Demo.isTriangle(9,13,5));
    }

    @Test
    public void test_isTriangle3() {
        assertTrue(Demo.isTriangle(13,5,9));
    }

    @Test
    public void test_isTriangle4() {
        assertTrue(Demo.isTriangle(5,13,9));

    }

    @Test
    public void test_isTriangle5() {
        assertTrue(Demo.isTriangle(9,5,13));
    }

    @Test
    public void test_isTriangle6() {
        assertTrue(Demo.isTriangle(13,9,5));
    }

    @Test
    public void test_isNotTriangle1() {
        assertFalse(Demo.isTriangle(3,8,12));
    }

    @Test
    public void test_isNotTriangle2() {
        assertFalse(Demo.isTriangle(3,12,8));
    }

    @Test
    public void test_isNotTriangle3() {
        assertFalse(Demo.isTriangle(8,3,12));
    }

    @Test
    public void test_isNotTriangle4() {
        assertFalse(Demo.isTriangle(8,12,3));
    }

    @Test
    public void test_isNotTriangle5() {
        assertFalse(Demo.isTriangle(12,3,8));
    }
    @Test
    public void test_isNotTriangle6() {
        assertFalse(Demo.isTriangle(12,8,3));
    }

    @Test
    public void test_isNotTriangle7() {
        assertFalse(Demo.isTriangle(3,3,-1));
    }
}
