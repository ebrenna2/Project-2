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
        System.out.println(bigNum.LListToString(list));
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
    }

}
