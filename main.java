import java.util.Stack;

public class BigNumArithmetic {
    public static void main(String[] args) {
        String line = "1 + 2 + 3";
        String result = addition(line);
        System.out.println(result);
    }

    public static String addition(String line) {
        Stack<String> stack = new Stack<>();
        String[] elements = line.split("\\s+");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].matches("-?\\d+")) {
                stack.push(elements[i]);
                /*String value = stack.get(i);
                stack.push(value);*/
            } else if (elements[i].equals("+")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough operands");
                }
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int sum = num1 + num2;
                stack.push(String.valueOf(sum));
            }
        }
        return stack.pop();
    }
}
