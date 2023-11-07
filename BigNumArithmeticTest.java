import static org.junit.Assert.*;
import org.junit.Test;

public class BigNumArithmeticTest {
    BigNumArithmetic bigNum = new BigNumArithmetic();
    @Test
    public void removeWhiteSpace() {
        String s;
        String expected;

        s = "14             5            +           ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("14 5 +", expected);

        s = "12                 3                   +   ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);

        s = "12             3        +       ";
        expected = bigNum.removeWhiteSpace(s);
        assertEquals("12 3 +", expected);
    }

    @Test
    public void deleteZeroes() {
        String s;
        String expected;

        s = "56";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        s = "00";
        expected = bigNum.deleteZeroes(s);
        assertEquals("0", expected);

        s = "056";
        expected = bigNum.deleteZeroes(s);
        assertEquals("56", expected);

        s = "000000000000000000761";
        expected = bigNum.deleteZeroes(s);
        assertEquals("761", expected);
    }

    @Test
    public void splitString() {
        LList split = new LList();
        String s = "12 3 +";
        String[] arr;

        arr = bigNum.splitString(s);
        for (String string : arr) {
            split.insert(string);
        }

        String done = bigNum.LListToString(split);
        assertEquals("123+", done);

    }

    @Test public void stringToLList() {
        String s;
        LList list;

        s = "5";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(5, list.getValue());

        s = "123";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

        s = "1 2 3";
        list = bigNum.stringToLList(s);
        list.moveToStart();
        assertEquals(3, list.getValue());
        list.next();
        assertEquals(2, list.getValue());
        list.next();
        assertEquals(1, list.getValue());

    }

    @Test
    public void LListToString(){
        LList list = new LList();
        String s;

        list.insert(9);
        s = bigNum.LListToString(list);
        assertEquals("9",s);

        list.clear();
        list.insert(9);
        list.insert(1);
        list.insert(6);
        s = bigNum.LListToString(list);
        assertEquals("916",s);

        list.clear();
        list.insert(2);
        list.insert(3);
        list.insert(5);
        s = bigNum.LListToString(list);
        assertEquals("235",s);
    }

    @Test
    public void addition() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        list.clear();
        list2.clear();
        result.clear();
        list.insert(99);
        list2.insert(9);

        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("108", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list2.insert(50000);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("50006", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(4);
        list2.insert(3);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("7", s);

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
        list.insert(5);
        list2.insert(2);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("27", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list.insert(5);
        list2.insert(2);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);

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

        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(0);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000000", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(4444);
        list2.insert(5556);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("10000", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(20);
        list2.insert(37);
        result = bigNum.add(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("57", s);

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

        list.clear();
        list2.clear();
        result.clear();
        list.insert(9);
        list.insert(9);
        list2.insert(9);

        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("891", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(6);
        list2.insert(50000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("300000", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(4);
        list2.insert(3);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12", s);

        list.clear();
        list2.clear();
        result.clear();
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
        list.insert(5);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(12345);
        list2.insert(1);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("12345", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(1000);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1000000", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(123);
        list2.insert(456);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("56088", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1230);
        list2.insert(4560);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("5608800", s);


        list.clear();
        list2.clear();
        result.clear();
        list.insert(0);
        list2.insert(0);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(12);
        list2.insert(40);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("480", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(3180);
        list2.insert(222);
        result = bigNum.multiply(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("705960", s);
    }

    @Test
    public void subtraction() {
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String s;

        list.clear();
        list2.clear();
        result.clear();

        list.insert(99);
        list2.insert(9);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("90", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(06);
        list2.insert(50000);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("49994", s);

        list.clear();
        list2.clear();
        result.clear();

        list.insert(100);
        list2.insert(100);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();

        list.insert(0001);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("998", s);

        list.clear();
        list2.clear();
        result.clear();

        list.insert(1000);
        list2.insert(999);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1", s);

        list.clear();
        list2.clear();
        result.clear();

        list.insert(3000);
        list2.insert(1234);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1766", s);

        // Subtracting zero
        list.clear();
        list2.clear();
        result.clear();

        list.insert(500);
        list2.insert(0);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

    // Subtracting from zero
        list.clear();
        list2.clear();
        result.clear();

        list.insert(0);
        list2.insert(500);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("500", s);

        list.clear();
        list2.clear();
        result.clear();


        list.clear();
        list2.clear();
        result.clear();

        list.insert(7);
        list2.insert(500);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("493", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(999999999);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999999998", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(123);
        list2.insert(45);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("78", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(100);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("99", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(123);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("877", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(5);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("0", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1000);
        list2.insert(1);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1);
        list2.insert(1000);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("999", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(5);
        list2.insert(10);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("5", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(1874);
        list2.insert(48);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("1826", s);

        list.clear();
        list2.clear();
        result.clear();
        list.insert(67);
        list2.insert(5);
        result = bigNum.subtraction(list, list2);
        s = bigNum.LListToString(result);
        assertEquals("62", s);
    }
    @Test
    public void testStringToLList_DeleteZeroes() {
        String token = "000123";
        LList result = bigNum.stringToLList(token);
        assertEquals("123", bigNum.LListToString(result));
    }

    @Test
    public void testValidLineOperatorWithPlus() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 +"));
        assertTrue(BigNumArithmetic.lineOperator("43 6 +"));
    }

    @Test
    public void testValidLineOperatorWithMinus() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 -"));
        assertTrue(BigNumArithmetic.lineOperator("395 111 -"));
    }

    @Test
    public void testValidLineOperatorWithMultipleNumbers() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 789 + +"));
        assertTrue(BigNumArithmetic.lineOperator("15 2 871 677 + + +"));
    }

    @Test
    public void testInvalidLineOperatorWithOperatorsOnly() {
        assertFalse(BigNumArithmetic.lineOperator("+ - / *"));
    }

    @Test
    public void testEmptyString() {
        assertFalse(BigNumArithmetic.lineOperator(""));
    }

    @Test
    public void testWhitespaceOnly() {
        assertFalse(BigNumArithmetic.lineOperator("   "));
    }

    @Test
    public void testValidLineOperatorWithTrailingSpaces() {
        assertTrue(BigNumArithmetic.lineOperator("123 456 +   "));
    }




}
