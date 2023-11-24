package lab10_task1_stack;

import java.util.Arrays;
import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> stack = new Stack<>();

		for (E e : array) {
			stack.push(e);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = stack.pop();
		}

	}

	// This method checks the correctness of the given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		Stack<Character> stack = new Stack<>();
		for (Character i : input.toCharArray()) {
			if (i == '(') {
				stack.push(')');
			} else if (i == '[') {
				stack.push(']');
			} else if (i == '{') {
				stack.push('}');
			} else if (stack.isEmpty() || stack.pop() != i) {
				return false;
			}

		}
		return stack.isEmpty();
	}

	// This method evaluates the value of anexpression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String expression) {
		expression = expression.replaceAll("\\s+","");
		Stack<Integer> operands = new Stack<>();
		Stack<Character> operators = new Stack<>();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (Character.isDigit(c)) {
				String s = c + "";
				while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
					s = s.concat(expression.charAt(++i) + "");
				}
				operands.push(Integer.valueOf(s));

			} else if (c == '+' || c == '-' || c == '*' || c == '/') {

				if(c == '-' && (i ==0 || expression.charAt(i - 1) == '(')){
                    operands.push(0);
                }

				while (!operators.isEmpty() && hasPrecedence(operators.peek()) >= hasPrecedence(c)) {
					calculate(operands, operators);
				}
				operators.push(c);
			} else if (c == '(') {
				operators.push(c);
			} else if (c == ')') {
				while (!operators.isEmpty() && operators.peek() != '(') {
					calculate(operands, operators);
				}
				operators.pop();

			}
		}
		while (!operators.isEmpty())

		{
			calculate(operands, operators);
		}

		return operands.pop();
	}

	private static int hasPrecedence(char c) {
		if (c == '+' || c == '-') {
			return 1;
		} else if (c == '*' || c == '/') {
			return 2;
		}
		return 0;
	}

	private static void calculate(Stack<Integer> operands, Stack<Character> operators) {
		int x = operands.pop();
		int y = operands.pop();
		int ops = operators.pop();
		switch (ops) {
		case '+': {
			operands.push(x + y);
			break;
		}
		case '-': {
			operands.push(y - x);
			break;
		}
		case '*': {
			operands.push(x * y);
			break;
		}
		case '/': {
			if (x == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			operands.push(y / x);
			break;
		}
		}

	}

	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 3, 4 };
		reserve(arr);
		System.out.println(Arrays.toString(arr));
		String s = "()     (())[]{(())}";
		String s2 = "){[]}()";
		System.out.println(isCorrect(s));
		System.out.println(isCorrect(s2));
		System.out.println(evaluateExpression("  51 - (   54 *(3+2))"));
		System.out.println(51 - (54 * (3 + 2)));
		System.out.println(evaluateExpression("1-(-2)"));
		System.out.println(evaluateExpression("-1-(-2)"));
		System.out.println(evaluateExpression("5-1-(-2)"));
		System.out.println(evaluateExpression("-(-2)"));
		System.out.println(evaluateExpression("(5+2)-3"));
		System.out.println(evaluateExpression("(1-(3-4))"));
		System.out.println(evaluateExpression("2-4-(8+2-6+(8+4-(1)+8-10))"));
		System.out.println(evaluateExpression("-(-(4+5))"));
		System.out.println(evaluateExpression("-(3-(-(4+5)))"));
		System.out.println(evaluateExpression("-(3-(       - (4+5)))"));

	}

}
