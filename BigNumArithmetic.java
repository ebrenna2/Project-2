import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * BigNumArithmetic class, where addition, multiplication, and subtraction of linked lists are done
 * @author Emma Brennan
 * @author Renzo Villalobos
 * @version 1.0
 *
 *
 *
 */

public class BigNumArithmetic {
    //main method
    public static void main(String[] args) {
        //try catch to catch the file not found exception
        try {
            if (args.length > 0) {
                //creates a new file input stream if the argument length is greater than 0
                //creates a fileinput stream for the file
                FileInputStream f = new FileInputStream(args[0]);
                //creates a new scanner for the file
                Scanner in = new Scanner(f);
                //creates a new linked stack
                LStack stack = new LStack();

                //while the scanner has a next line
                while (in.hasNextLine()) {
                    //clears the stack
                    stack.clear();
                    String s = in.nextLine();
                    //removes the white space
                    s = removeWhiteSpace(s);
                    //splits the string
                    String[] expressions = splitString(s);

                    //for loop to go through the expressions
                    for (int i = 0; i < expressions.length; i++) {
                        //creates a new token
                        String token = expressions[i];
                        //if the token is a +, add the two linked lists
                        if ("+".equals(token)) {
                            //pops the stack
                            LList tempB = (LList) stack.pop();
                            LList tempA = (LList) stack.pop();
                            //adds the two linked lists
                            LList added = add(tempA, tempB);
                            stack.push(added);
                        }
                        //if the token is a -, subtract the two linked lists
                        else if ("-".equals(token)) {
                            //pops the stack
                            LList tempB = (LList) stack.pop();
                            LList tempA = (LList) stack.pop();
                            //subtracts the two linked lists
                            LList subtracted = subtraction(tempA, tempB);
                            stack.push(subtracted);
                        }
                        //if the token is a *, multiply the two linked lists
                        else if ("*".equals(token)) {
                            //pops the stack
                            LList tempB = (LList) stack.pop();
                            LList tempA = (LList) stack.pop();
                            //multiplies the two linked lists
                            LList multiplied = multiply(tempA, tempB);
                            stack.push(multiplied);
                        }
                        else {
                            //converts the token to a linked list
                            LList temp = stringToLList(deleteZeroes(token));
                            stack.push(temp);
                        }
                    }

                    //if the stack length is 1 and the line operator is true, print the result
                    if (stack.length() == 1 && lineOperator(s)) {
                        LList result = (LList) stack.pop();
                        System.out.print(s.replaceAll("\\s+", " ") + " = " + LListToString(result) + "\n");
                    }
                    //else, prints the line operator
                    else if (lineOperator(s)) {
                        System.out.print(s.replaceAll("\\s+", " ") + " =\n");
                    }
                }

            }
        }
        //catches the file not found exception
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
        }
    }


    /**
     * splitString, splits the string
     * @param s  the string to be split
     *           @return the string array of the split string
     *
     */
    public static String[] splitString(String s) {
        //splits the string
        return s.split("\\s+");
    }

    /**
     * removeWhiteSpace, removes the white space
     * @param s  the string to be removed of white space
     *           @return the string without white space
     *
     */
    public static String removeWhiteSpace(String s) {
        String result = s;
        //removes the white space
        result = result.trim().replaceAll(" +", " ");
        //returns the result
        return result;
    }

    /**
     * deleteZeroes, deletes the zeroes
     * @param s  the string to be deleted of zeroes
     *           @return the string without zeroes
     *
     */
    public static String deleteZeroes(String s) {
        //while the string is not empty and the character at the first index is 0, increment i
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        //returns the substring
        return i < s.length() ? s.substring(i) : "0";
    }


    /**
     * stringToLList, converts the string to a linked list
     * @param s  the string to be converted to a linked list
     *           @return the linked list of the string
     *
     */
    public static LList stringToLList(String s) {
        //creates a new linked list
        LList result = new LList();
        //if the string is null or empty, append 0 to the linked list
        if (s == null || s.isEmpty()) {
            result.append(0);
            return result;
        }
        //for loop to go through the string
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            //if the character is a digit, append the character to the linked list
            if (Character.isDigit(ch)) {
                result.append(ch - '0');
            }
        }
        return result;
    }



    /**
     * LListToString, converts the linked list to a string
     * @param, list  the linked list to be converted to a string
     *           @return the string of the linked list
     *
     */
    public static String LListToString(LList list) {
        //creates a new string
        String result = "";
        //moves to start
        list.moveToStart();
        //while the current position is less than the length of the list, add the value to the result
        while (list.currPos() < list.length()) {
            //adds the value to the result
            result = list.getValue() + result;
            // Move to the next digit in each list
            list.next();
        }
        //returns the result
        return result;
    }
    /**
     * add, where the addition of two linked lists is performed
     *
     * @param a  the first linked list to be added
     *           @param b  the second linked list to be added
     *                     @return the linked list of the addition of the two linked lists
     *
     */
    public static LList add(LList a, LList b) {
        //creates a new linked list for the sum
        LList sumList = new LList();
        //creates a variable for the carry
        int carry = 0;

        if (a == null) {
            a = new LList();
        }
        if (b == null) {
            b = new LList();
        }
        
        //if the length of a is greater than the length of b, append 0 to b
        if (a.length() > b.length()) {
            int count = a.length() - b.length();
            for(int i = 0; i < count; i++){
                b.append(0);
            }
        }
        //if the length of b is greater than the length of a, append 0 to a
        else
        if (b.length() > a.length()){
            int count = b.length() - a.length();
            for(int i = 0; i < count; i++){
                a.append(0);
            }
        }
        //moves to start
        a.moveToStart();
        //moves to start
        b.moveToStart();

        //while the current position is less than the length of the list
        while(a.currPos() < a.length()) {

            //adds the value of a, the value of b, and the carry
            int sum = (int) a.getValue() + (int) b.getValue() + carry;

            //sets the carry to the sum divided by 10
            carry = sum / 10;

            //sets the sum to the sum mod 10
            sum = sum % 10;


            //appends the sum to the sum list
            sumList.append(sum);


            // Move to the next digit in each list
            a.next();
            b.next();
        }

        //if the carry is greater than 0, append the carry to the sum list
        if (carry > 0){
            sumList.append(carry);
        }

        //returns the sum list
        return sumList;
    }

    /**
     * multiply, where the multiplication of two linked lists is performed
     *
     * @param a  the first linked list to be multiplied
     *           @param b  the second linked list to be multiplied
     *                     @return the linked list of the multiplication of the two linked lists
     *
     */
    public static LList multiply(LList a, LList b) {
        //if a or b is zero, return zero
        if (isZero(a) || isZero(b)) {
            //makes a new linked list for the zero
            LList zeroList = new LList();
            //adds a zero to the list
            zeroList.append(0);
            //returns the zero list
            return zeroList;
        }
        //creates a llist for the result
        LList result = new LList();
        LList tempResult;

        //moves to start
        b.moveToStart();
        //makes a variable for the shift
        int shift = 0;

        //while the current position is less than the length of the list, add the value to the result
        while (b.currPos() < b.length()) {
            tempResult = new LList();
            int carry = 0;
            int bValue = (int) b.getValue();

            //moves to start
            a.moveToStart();

            //adds a zero to the list
            for (int i = 0; i < shift; i++) {
                tempResult.append(0);
            }

            //while the current position is less than the length of the list, add the value to the result
            while (a.currPos() < a.length()) {
                // Multiply the values and the carry
                int product = (int) a.getValue() * bValue + carry;

                // Set the carry to the tens digit
                carry = product / 10;
                // Set the product to the ones digit
                product = product % 10;
                // Append the product to the result
                tempResult.append(product);
                // Move to the next digit in each list
                a.next();
            }

            // If there is a carry, append it to the result
            if (carry > 0) {
                tempResult.append(carry);
            }
            // Add the temporary result to the result
            result = add(result, tempResult);
            // Move to the next digit in each list
            b.next();
            // Increment the shift
            shift++;
        }
        return result;
    }

    /**
     * lineOperator, checks for if the string contains a line operator
     * @param s  the string to be checked
     *           @return true if the string contains a line operator, false otherwise
     *
     */
    public static boolean lineOperator(String s) {
        //returns true if the string contains a +, *, or -, false otherwise
        return s != null && (s.contains("+") || s.contains("*") || s.contains("-"));
    }

    /**
     * isgreaterorequal, checks for if the first linked list is greater than or equal to the second linked list
     * @param a  the first linked list to be checked
     *           @param b  the second linked list to be checked
     *                     @return true if the first linked list
     *                     is greater than or equal to the second linked list, false otherwise
     *
     */
    private static boolean isGreaterOrEqual(LList a, LList b) {
        //if the length of a is greater than the length of b, return true
        if (a.length() > b.length()) return true;
        //if the length of a is less than the length of b, return false
        if (a.length() < b.length()) return false;


        // Move to the end of each list
        a.moveToEnd();
        // Move to the end of each list
        b.moveToEnd();

        // While neither list is at the end
        while (!a.isAtEnd() && !b.isAtEnd()) {
            // Get the values at the current position
            int valA = (int) a.getValue();
            // Get the values at the current position
            int valB = (int) b.getValue();

            // If the value in a is greater than the value in b, return true
            if (valA > valB) {
                return true;
            }
            // If the value in a is less than the value in b, return false
            if (valA < valB) {
                return false;
            }
            // Move to the previous digit in each list
            a.prev();
            // Move to the previous digit in each list
            b.prev();
        }

        // If the lists are equal, return true
        return true;
    }

    /**
     * iszero, checks for if the linked list is zero
     * @param list  the linked list to be checked
     *           @return true if the linked list is zero, false otherwise
     *
     */
    public static boolean isZero(LList list) {
        if (list == null) {
            return true;
        }
        //moves to start
        list.moveToStart();
        //checks if the current position is less than the length of the list
        while (list.currPos() < list.length()) {
            //if the value is not zero, return false
            if ((int) list.getValue() != 0) {
                return false;
            }
            // Move to the next digit in each list
            list.next();
        }
        //return true
        return true;

    }

    /**
     * subtraction, where the subtraction of two linked lists is performed
     *
     * @param a  the first linked list to be subtracted
     *           @param b  the second linked list to be subtracted
     *                     @return the linked list of the subtraction of the two linked lists
     *
     */
    public static LList subtraction(LList a, LList b) {
        //converns the linked lists to strings, deletes the zeroes
        a = stringToLList(deleteZeroes(LListToString(a)));
        b = stringToLList(deleteZeroes(LListToString(b)));

        //if a is less than b, switch them
        if (!isGreaterOrEqual(a, b)) {
            LList temp = a;
            a = b;
            b = temp;
        }

        //creates a new linked list for the result
        LList result = new LList();

        //creates a variable for the borrow
        int borrow = 0;

        //moves to start
        a.moveToStart();
        //moves to start
        b.moveToStart();

        //while the current position is less than the length of the list, add the value to the result
        while (a.currPos() < a.length() || b.currPos() < b.length() || borrow != 0) {
            int valA = a.currPos() < a.length()
                    ? (int) a.getValue() : 0;
            int valB = b.currPos() < b.length()
                    ? (int) b.getValue() : 0;

            // Subtract the values and the borrow
            int difference = valA - valB - borrow;

            // If the difference is negative, add 10 and set the borrow to 1
            if (difference < 0) {
                difference += 10;
                borrow = 1;
            }
            // Otherwise, set the borrow to 0
            else {
                borrow = 0;
            }

            // Append the difference to the result
            result.append(difference);

            // Move to the next digit in each list
            if (a.currPos() < a.length()) {
                a.next();
            }

            // Move to the next digit in each list
            if (b.currPos() < b.length()) {
                b.next();
            }
        }

        // Delete leading zeroes from the result
        result = stringToLList(deleteZeroes(LListToString(result)));

        return result;
    }

}