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
}
