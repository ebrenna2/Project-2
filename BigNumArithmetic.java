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
                        }

                        else {
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
        return result;
    }

    public static String LListToString(LList list) {
        String result = "";
        list.moveToStart();
        while (list.currPos() < list.length()) {
            result = list.getValue() + result;
            list.next();
        }
        return result;
    }

    public static LList add(LList a, LList b) {
        LList sumList = new LList();
        int carry = 0;


        if (a.length() > b.length()) {
            int count = a.length() - b.length();
            for(int i = 0; i < count; i++){
                b.append(0);
            }
        }
        if (b.length() > a.length()){
            int count = b.length() - a.length();
            for(int i = 0; i < count; i++){
                a.append(0);
            }
        }


        a.moveToStart();
        b.moveToStart();


        while(a.currPos() < a.length()) {

            int sum = (int) a.getValue() + (int) b.getValue() + carry;

            carry = sum / 10;

            sum = sum % 10;


            sumList.append(sum);


            a.next();
            b.next();
        }

        if (carry > 0){
            sumList.append(carry);
        }

        return sumList;
    }

    public static LList multiply(LList a, LList b) {
        if (isZero(a) || isZero(b)) {
            LList zeroList = new LList();
            zeroList.append(0);
            return zeroList;
        }
        LList result = new LList();
        LList tempResult;

        b.moveToStart();
        int shift = 0;

        while (b.currPos() < b.length()) {
            tempResult = new LList();
            int carry = 0;
            int bValue = (int) b.getValue();

            a.moveToStart();

            for (int i = 0; i < shift; i++) {
                tempResult.append(0);
            }

            while (a.currPos() < a.length()) {
                int product = (int) a.getValue() * bValue + carry;

                carry = product / 10;
                product = product % 10;
                tempResult.append(product);
                a.next();
            }

            if (carry > 0) {
                tempResult.append(carry);
            }
            result = add(result, tempResult);
            b.next();
            shift++;
        }
        return result;
    }

    //fix so it deals with mulitplication and subtraction?
    public static boolean lineOperator(String s) {
        return s != null && (s.contains("+") || s.contains("*") || s.contains("-"));
    }

    public static boolean isZero(LList list) {
        list.moveToStart();
        while (list.currPos() < list.length()) {
            if ((int) list.getValue() != 0) {
                return false;
            }
            list.next();
        }
        return true;
    }


}
