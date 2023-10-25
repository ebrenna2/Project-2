import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class BigNumArithmetic {
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                FileInputStream f = new FileInputStream(args[0]);
                Scanner in = new Scanner(f);
                LStack stack = new LStack();

                while (in.hasNextLine()) {
                    stack.clear();
                    String s = in.nextLine();
                    s = removeWhiteSpace(s);
                    String[] expressions = splitString(s);

                    for (int i = 0; i < expressions.length; i++) {
                        String token = expressions[i];
                        if ("+".equals(token)) {
                            while (stack.length() > 1 && i < expressions.length && "+".equals(expressions[i])) {
                                LList tempB = (LList) stack.pop();
                                LList tempA = (LList) stack.pop();
                                LList added = add(tempA, tempB);
                                stack.push(added);
                                i++;
                            }
                            i--;
                        } else {
                            LList temp = stringToLList(deleteZeroes(token));
                            stack.push(temp);
                        }
                    }

                    if (stack.length() == 1 && lineOperator(s)) {
                        LList result = (LList) stack.pop();
                        System.out.print(s.replaceAll("\\s+", " ") + " = " + LListToString(result) + "\n");
                    } else if (lineOperator(s)) {
                        System.out.print(s.replaceAll("\\s+", " ") + " =\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
        }
    }


    public static String[] splitString(String s) {
        return s.split("\\s+");
    }

    public static String removeWhiteSpace(String s) {
        String result = s;
        result = result.trim().replaceAll(" +", " ");
        return result;
    }

    public static String deleteZeroes(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        return i < s.length() ? s.substring(i) : "0";
    }


    public static LList stringToLList(String s) {
        LList result = new LList();
        if (s == null || s.isEmpty()) {
            result.append(0);
            return result;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                result.append(ch - '0');
            }
        }
        return removeLeadingZerosFromLList(result);
    }



    public static String LListToString(LList l) {
        StringBuilder result = new StringBuilder();
        l.moveToStart();
        while (l.currPos() < l.length()) {
            result.insert(0, l.getValue());
            l.next();
        }
        return result.toString();
    }

    public static LList add(LList a, LList b) {
        LList result = new LList();
        int carry = 0;

        LList tempA = copyLList(a);
        LList tempB = copyLList(b);

        tempA.moveToStart();
        tempB.moveToStart();

        while(tempA.currPos() < tempA.length() || tempB.currPos() < tempB.length()) {
            int valA = tempA.currPos() < tempA.length() ? (int) tempA.getValue() : 0;
            int valB = tempB.currPos() < tempB.length() ? (int) tempB.getValue() : 0;

            int sum = valA + valB + carry;
            carry = sum / 10;
            sum = sum % 10;
            result.append(sum);

            if (tempA.currPos() < tempA.length()) tempA.next();
            if (tempB.currPos() < tempB.length()) tempB.next();
        }
        if (carry > 0) {
            result.append(carry);
        }

        return removeLeadingZerosFromLList(result);
    }

    public static boolean lineOperator(String s) {
        return s != null && s.contains("+");
    }

    public static LList copyLList(LList original) {
        LList copy = new LList();
        original.moveToStart();
        while(original.currPos() < original.length()) {
            copy.append(original.getValue());
            original.next();
        }
        return copy;
    }

    public static LList removeLeadingZerosFromLList(LList l) {
        LList result = new LList();
        boolean nonZeroFound = false;
        l.moveToStart();
        while (l.currPos() < l.length()) {
            int value = (int) l.getValue();
            if (value != 0 || nonZeroFound) {
                result.append(value);
                nonZeroFound = true;
            }
            l.next();
        }

        if (result.length() == 0) {
            result.append(0);
        }

        return result;
    }

}