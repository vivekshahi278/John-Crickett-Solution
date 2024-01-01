import java.util.*;
public class own_calculator_solution {
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : infix.toCharArray()) {
            if (isOperand(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }
    private static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else if (isOperator(c)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(c, operand1, operand2);
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid character in postfix expression: " + c);
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
    }
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String a= sc.next();
        System.out.println(evaluatePostfix(infixToPostfix(a)));
    }
    
}
