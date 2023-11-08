import static org.junit.Assert.*;
import org.junit.Test;

//test class for testing bignumarithmetic
public class BigNumArithmeticTest {
    BigNumArithmetic bigNum = new BigNumArithmetic();

    //test for removing white space with spaces, tabs, and when there's only a small amt of space
    @Test
    public void removeWhiteSpace() {
        String s;
        String expected;

        //test for spacebar whitespace
        s = "14             5            +           ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("14 5 +", expected);

        //test for tab whitespace
        s = "12                 3                   +   ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);

        //test for a mix of spacebar and tab whitespace
        s = "12             3        +       ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);
    }

    //tests for deleting zeroes when there are no zeroes, 00, 056, which should be 56, when there are a lot of leading zeroes, and then when there's a zero at the end
    @Test
    public void deleteZeroes() {
        String s;
        String expected;

        //test for when there aren't any leading zeroes
        s = "56";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        //test for when there is one leading 0 but the next number is also 0
        s = "00";
        expected = bigNum.deleteZeroes(s);
        assertEquals("0", expected);

        //tests for when there is one leading 0
        s = "056";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        //test for when there are multiple leading 0's
        s = "000000000000000000761";
        expected = bigNum.deleteZeroes(s);
        assertEquals("761", expected);

        //tests for when the 0 is at the end
        s = "7610";
        expected = bigNum.deleteZeroes(s);
        assertEquals("7610", expected);
    }

    //tests for the string being split accurately, and removing all the spaces in it
    @Test
    public void splitString() {
        LList split = new LList();
        //make a string with numbers and an operator
        String s = "12 3 +";
        String[] arr;

        //then split the string accordingly
        arr = bigNum.splitString(s);
        for (String string : arr) {
            split.insert(string);
        }

        //then convert to a string again and make sure the output is right
        String done = bigNum.LListToString(split);
        assertEquals("123+", done);

    }

    //tests making a string into a llist and making sure it reverses the numbers properly
    @Test public void stringToLList() {
        //make a string and a list to store the results
        String s;
        LList list;

        //test for just one digit
        s = "5";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(5, list.getValue());

        //test for multiple digits
        s = "123";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

        //test for multiple digits with spaces inbetween
        s = "1 2 3";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

    }

    //tests to make sure it properly converts a llist to a string
    @Test
    public void LListToString(){
        //make a list and string s with the results
        LList list = new LList();
        String s;

        //test for only one digit
        list.insert(9);
        s = bigNum.LListToString(list);
        assertEquals("9",s);

        //another test for only one digit
        list.clear();
        list.insert(48);
        s = bigNum.LListToString(list);
        assertEquals("48", s);

        //test for multiple digits
        list.clear();
        list.insert(9);
        list.insert(1);
        list.insert(6);
        s = bigNum.LListToString(list);
        assertEquals("916",s);

        //another test for multiple digits
        list.clear();
        list.insert(2);
        list.insert(3);
        list.insert(5);
        s = bigNum.LListToString(list);
        assertEquals("235",s);
    }

    //tests to sure it properly adds
    @Test
    public void addition() {
        //make a list, list2, and result
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        //test for adding a two digit and one-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(99);
        list2.insert(9);

        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("108", s);

        //test for adding a multiple digit number and a one digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list2.insert(50000);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("50006", s);

        //test for adding two one-digit numbers
        list.clear();
        list2.clear();
        result.clear();
        list.insert(4);
        list2.insert(3);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("7", s);

        //test for adding two one-digit numbers with a carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(7);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12", s);

        //test for adding one two-digit number and a one-digit number with no carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(2);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("27", s);

        //another test for adding one two-digit number and a one-digit number with no carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list.insert(5);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);

        //adding two two-digit numbers with a carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1);
        list.insert(3);
        list2.insert(3);
        list2.insert(1);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("44", s);

        //adding two two-digit numbers with a carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list.insert(5);
        list2.insert(4);
        list2.insert(3);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("108", s);

        //adding two 3-digit numbers
        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list.insert(5);
        list.insert(7);
        list2.insert(4);
        list2.insert(3);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1089", s);

        //adding a multiple digit number and 0 to the number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(0);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        //adding 1 to a multiple digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000000", s);

        //adding two 4-digit numbers and carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(4444);
        list2.insert(5556);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("10000", s);

        //adding two two-digit numbers again
        list.clear();
        list2.clear();
        result.clear();
        list.insert(20);
        list2.insert(37);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);

        //add a leading 0 to the 7, add to a two-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(07);
        list2.insert(50);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);
    }

    @Test
    public void multiplication() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        //test case for multiplying a two-digit number by a one-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(99);
        list2.insert(9);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("891", s);

        //test case for multiplying a one-digit number by a multiple digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list2.insert(50000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("300000", s);

        //test for multiplying two one-digit numbers together, with carry
        list.clear();
        list2.clear();
        result.clear();
        list.insert(4);
        list2.insert(3);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12", s);

        //test case for multiplying two one-digit numbers
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(7);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("35", s);

        //test case for multiplying a number by 0, which gives 0
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //another test case for multiplying a number by 0, but with a larger number this time
        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //test case for multiplying a number by 1, which gives itself
        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(1);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        //test case for making sure it multiplies properly with multiple zeroes
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(1000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000", s);

        //test case with multiplying two three-digit numbers together
        list.clear();
        list2.clear();
        result.clear();
        list.insert(123);
        list2.insert(456);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("56088", s);

        //another test case for multiplying multiple digit numbers together, just a bit more advanced
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1230);
        list2.insert(4560);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("5608800", s);

        //test case for multiplying 0 by 0, which gives 0
        list.clear();
        list2.clear();
        result.clear();
        list.insert(0);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //test case for multiplying two two-digit numbers together
        list.clear();
        list2.clear();
        result.clear();
        list.insert(12);
        list2.insert(40);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("480", s);

        //test case for multiplying a multiple digit number and a 3-digit number together
        list.clear();
        list2.clear();
        result.clear();
        list.insert(3180);
        list2.insert(222);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("705960", s);

        //test case for multiplying two four-digit numbers
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1234);
        list2.insert(9876);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12186984", s);
    }

    //test cases for subtraction
    @Test
    public void subtraction() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        //test case for subtracting a two-digit number from a one-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(99);
        list2.insert(9);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("90", s);

        //test case with a leading zero, making sure it properly subtracts and gets rid of the leading 0
        list.clear();
        list2.clear();
        result.clear();
        list.insert(06);
        list2.insert(50000);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("49994", s);

        //test case to make sure it properly subtracts a number by itself and gives 0
        list.clear();
        list2.clear();
        result.clear();
        list.insert(100);
        list2.insert(100);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //test case for a number with multiple leading 0s and making sure it properly subtracts and gets rid of the leading 0's
        list.clear();
        list2.clear();
        result.clear();
        list.insert(0001);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("998", s);

        //test case to make sure it is properly subtracted (multiple digits) and gives 1
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1", s);

        //test case to make sure it subtracts numbers with 4 digits each properly
        list.clear();
        list2.clear();
        result.clear();
        list.insert(3000);
        list2.insert(1234);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1766", s);

        // Subtracting zero
        //test case to make sure it subtracts 3-digit number by 0
        list.clear();
        list2.clear();
        result.clear();
        list.insert(500);
        list2.insert(0);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

        // Subtracting from zero
        //test case to make sure it subtracts 0 by a 3-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(0);
        list2.insert(500);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

        //test to make sure it doesn't give negative for subtracting 7 - 500
        list.clear();
        list2.clear();
        result.clear();
        list.insert(7);
        list2.insert(500);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("493", s);

        //test to make sure it works properly for subtracting a large number by 1
        list.clear();
        list2.clear();
        result.clear();
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999999998", s);

        //test case to make sure it properly subtracts a 3-digit number by a 2-digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(123);
        list2.insert(45);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("78", s);

        //test case to make sure it properly subtracts 100 by 1
        list.clear();
        list2.clear();
        result.clear();
        list.insert(100);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("99", s);

        //test case to make sure it subtracts a large number by a smaller number properly
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(123);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("877", s);

        //test case to make sure it returns 0 when the numbers being subtracts are the same
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(5);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //test case to make it return 0 when 2 2-digit numbers that are the same are subtracted
        list.clear();
        list2.clear();
        result.clear();
        list.insert(91);
        list2.insert(91);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        //test case for subtracting a 4 digit number by a one digit number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999", s);

        //test case to make sure it gives a non-negative number with doing 1 - 1000, = 999, which is expected
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1);
        list2.insert(1000);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999", s);

        //test case for subtracting 5 by 10 (the same), which should give 5 and not negative
        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(10);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("5", s);

        //test case for subtracting a larger number by a smaller number
        list.clear();
        list2.clear();
        result.clear();
        list.insert(1874);
        list2.insert(48);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1826", s);

        //test case for subtracting a two digit by one digit
        list.clear();
        list2.clear();
        result.clear();
        list.insert(67);
        list2.insert(5);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("62", s);
    }


    //tests to make sure two numbers are valid with the addition sign
    @Test
    public void testValidLineOperatorWithPlus() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 +"));
        assertTrue(BigNumArithmetic.lineOperator("43 6 +"));
        assertTrue(BigNumArithmetic.lineOperator("384 55 +"));
        assertTrue(BigNumArithmetic.lineOperator("21 511 +"));
        assertTrue(BigNumArithmetic.lineOperator("91 75 +"));
    }

    //tests to make sure multiple numbers with the subtraction signs are valid - have enough operators
    @Test
    public void testValidLineOperatorWithMinus() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 -"));
        assertTrue(BigNumArithmetic.lineOperator("395 111 -"));
        assertTrue(BigNumArithmetic.lineOperator("2 1 -"));
        assertTrue(BigNumArithmetic.lineOperator("27 55 -"));
        assertTrue(BigNumArithmetic.lineOperator("672 48 -"));
    }

    //tests to make sure multiple numbers with the addition and subtraction signs are valid - have enough operators
    @Test
    public void testValidLineOperatorWithMultipleNumbers() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 789 + +"));
        assertTrue(BigNumArithmetic.lineOperator("15 2 871 677 + + +"));
        assertTrue(BigNumArithmetic.lineOperator("123 456 789 - -"));
        assertTrue(BigNumArithmetic.lineOperator("84 26 14 65 - - -"));
    }


    //test to make sure division isn't a valid line operator
    @Test
    public void testInvalidLineOperatorWithOperatorsOnly() {
        assertFalse(BigNumArithmetic.lineOperator("/"));
    }

    //test to make sure blanks are not valid
    @Test
    public void testEmptyString() {
        assertFalse(BigNumArithmetic.lineOperator(""));
    }

    //test to make sure white spaces aren't valid
    @Test
    public void testWhitespaceOnly() {
        assertFalse(BigNumArithmetic.lineOperator("   "));
    }

    //test to make sure the + is a valid line operator
    @Test
    public void testValidLineOperatorWithTrailingSpaces() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 +   "));
    }

}
