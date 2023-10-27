import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Emma Brennan
 * @author Renzo Villalobos
 * this class tests the various methods from bignumarithmetic.java, such as removing white space, deleting zeroes
 * from a string, splitting a string (removing its white spaces), converting a string to llist, a llist to a string, addition, subtraction
 * and multiplication, various different scenarios with each
 *
 */
public class BigNumArithmeticTest {
    //creates new bignum class
    BigNumArithmetic bigNum = new BigNumArithmetic();

    /**
     * tests the removeWhiteSpace method with a variety of different strings
     */
    @Test
    public void removeWhiteSpace() {
        String s;
        String expected;

        //tests a string with a lot of white space
        s = "14             5            +           ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("14 5 +", expected);

        //tests a string with tabs
        s = "12                 3                   +   ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);

        //tests a string with tabs and spaces
        s = "12             3        +       ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);

        //tests without any white space
        s= "123+";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("123+", expected);
    }

    /**
     * tests the deleteZeroes method with a variety of different strings
     */
    @Test
    public void deleteZeroes() {
        String s;
        String expected;

        //tests for no zeroes
        s = "56";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        //tests for two zeroes (one leading)
        s = "00";
        expected = bigNum.deleteZeroes(s);
        assertEquals("0", expected);

        //tests for a leading 0
        s = "056";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        //tests for a bunch of leading zeroes
        s = "000000000000000000761";
        expected = bigNum.deleteZeroes(s);
        assertEquals("761", expected);

        //makes sure none of the zeroes after are deleted
        s = "56000";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56000", expected);
    }

    /**
     * tests the splitString method with a variety of different strings
     */
    @Test
    public void splitString() {
        LList split = new LList();
        //make a new string to be split
        String s = "12 3 +";
        String[] arr;

        //split the string
        arr = bigNum.splitString(s);
        for (String string : arr) {
            split.insert(string);
        }

        //make sure the string was split correctly
        String done = bigNum.LListToString(split);
        assertEquals("123+", done);

    }

    /**
     * tests the stringToLList method with a variety of different strings
     */
    @Test public void stringToLList() {
        String s;
        LList list;

        //tests for a string with no spaces
        s = "5";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(5, list.getValue());

        //tests for a string with multiple nums
        s = "123";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

        //tests for a string with multiple spaces
        s = "1 2 3";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

        //tests for a string that has spaces inbetween
        s = "12 3";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

        //tests for space in the front and the back
        s = "  12 3 ";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

    }

    /**
     * tests the LListToString method with a variety of different strings
     */
    @Test
    public void LListToString(){
        LList list = new LList();
        String s;

        //tests for a llist with no spaces, single digit
        list.insert(9);
        s = bigNum.LListToString(list);
        assertEquals("9",s);

        list.clear();

        //tests for a llist with no spaces, multiple digits
        list.insert(9);
        list.insert(1);
        list.insert(6);
        s = bigNum.LListToString(list);
        assertEquals("916",s);

        //test for a llist with multiple digits
        list.clear();
        list.insert(2);
        list.insert(3);
        list.insert(5);
        s = bigNum.LListToString(list);
        assertEquals("235",s);
    }

    /**
     * tests the addition method with a variety of different strings
     */
    @Test
    public void addition() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        list.clear();
        list2.clear();
        result.clear();

        //tests for two numbers, one two digit and one 1 digit
        list.insert(99);
        list2.insert(9);

        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("108", s);

        list.clear();
        list2.clear();
        result.clear();

        //tests for two numbers, one multiple digits and one 1 digit
        list.insert(6);
        list2.insert(50000);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("50006", s);

        list.clear();
        list2.clear();
        result.clear();

        //tests for two numbers, both one digit
        list.insert(4);
        list2.insert(3);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("7", s);

        //carry over test with two numbers, two digits
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(7);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12", s);

        list.clear();
        list2.clear();
        result.clear();

        //test with two numbers, one one digit, one two digit
        list.insert(5);
        list2.insert(2);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("27", s);

        list.clear();
        list2.clear();
        result.clear();

        //test with two numbers, one one digit, one two digit
        list.insert(5);
        list.insert(5);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);

        list.clear();
        list2.clear();
        result.clear();

        //test with two numbers, both two digits, no carry over
        list.insert(1);
        list.insert(3);
        list2.insert(3);
        list2.insert(1);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("44", s);

        list.clear();
        list2.clear();
        result.clear();

        //test with two numbers, both two digits, carry over
        list.insert(6);
        list.insert(5);
        list2.insert(4);
        list2.insert(3);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("108", s);

        list.clear();
        list2.clear();
        result.clear();

        //test with two numbers, both two digits, carry over
        list.insert(6);
        list.insert(5);
        list.insert(7);
        list2.insert(4);
        list2.insert(3);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1089", s);

        list.clear();
        list2.clear();
        result.clear();
        //test with two numbers, adding 0, so stays the same
        list.insert(12345);
        list2.insert(0);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        list.clear();
        list2.clear();
        result.clear();
        //test with two numbers, carry over
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000000", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests big numbers
        list.insert(4444);
        list2.insert(5556);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("10000", s);

    }

    @Test
    public void multiplication() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        list.clear();
        list2.clear();
        result.clear();

        //test for two numbers, one two digit and one 1 digit
        list.insert(99);
        list2.insert(9);

        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("891", s);

        list.clear();
        list2.clear();
        result.clear();

        //test for two numbers, one multiple digits and one 1 digit, larger numbers
        list.insert(6);
        list2.insert(50000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("300000", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests for one digit smaller numbers
        list.insert(4);
        list2.insert(3);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests for one digit smaller numbers
        list.insert(5);
        list2.insert(7);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("35", s);

        list.clear();
        list2.clear();
        result.clear();

        list.clear();
        list2.clear();
        result.clear();
        //tests to make sure the result is 0 when multiplied by 0
        list.insert(5);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests to make sure the result is 0 when multiplied by 0
        list.insert(12345);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);


        list.clear();
        list2.clear();
        result.clear();
        //tests to make sure the result is the first num when multiplied by 1
        list.insert(12345);
        list2.insert(1);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests to make sure the result is right with large numbers
        list.insert(1000);
        list2.insert(1000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000", s);

        list.clear();
        list2.clear();
        result.clear();
        //tests to make sure the result is right with large numbers, more complex
        list.insert(123);
        list2.insert(456);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("56088", s);

        list.clear();
        list2.clear();
        result.clear();
        //test for when the first number is 0
        list.insert(0);
        list2.insert(9);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        //test for when both are 0
        list.insert(0);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);
    }

    //tests subtracting numbers
    @Test
    public void subtraction() {
        //creates new llists and a string
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        //clears the lists, etc
        list.clear();
        list2.clear();
        result.clear();

        //inserts 99 and 9 into the list
        list.insert(99);
        list2.insert(9);
        //sets result to bignum subtraction, and subtracts the two llists
        result = bigNum.subtraction(list, list2);
        //sets string to the result
        s = bigNum.LListToString(result);
        //checks if the result is correct
        assertEquals("90", s);

        //clears out the list
        list.clear();
        list2.clear();
        result.clear();
        //inserts 6 and 50000 to the two diff lists
        list.insert(6);
        list2.insert(50000);
        //sets the result
        result = bigNum.subtraction(list, list2);
        //sets the string to the result, converts it
        s = bigNum.LListToString(result);
        assertEquals("49994", s);

        list.clear();
        list2.clear();
        result.clear();

        //make sure it does the reverse, when the first num is smaller
        list.insert(0001);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("998", s);

        list.clear();
        list2.clear();
        result.clear();

        //make sure it leaves 1
        list.insert(1000);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1", s);

        list.clear();
        list2.clear();
        result.clear();

        //larger number subtraction
        list.insert(3000);
        list2.insert(1234);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1766", s);

        list.clear();
        list2.clear();
        result.clear();

        // Subtracting zero
        list.insert(500);
        list2.insert(0);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

        list.clear();
        list2.clear();
        result.clear();

        //make sure its not negative when the first number is smaller (again)
        list.insert(0);
        list2.insert(500);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

        list.clear();
        list2.clear();
        result.clear();
        //make sure it gives the right result with a big num
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999999998", s);
    }
}
