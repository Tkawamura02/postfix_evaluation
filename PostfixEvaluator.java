import java.util.Scanner;

public class PostfixEvaluator {
	public static int evaluate(String expr) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(100);
		Scanner parser = new Scanner(expr);

		while (parser.hasNext()) {
			String token = parser.next();

			if ("+-*/".indexOf(token) >= 0) {
				Integer op2 = stack.pop();
				Integer op1 = stack.pop();
				if (op1 == null || op2 == null) {
					throw new java.lang.ArithmeticException("Insufficient operands for " + token + ".");
				}
				stack.push(evaluateSingleOperator(token.charAt(0), op1, op2));
			} else {
				stack.push(Integer.parseInt(token));
			}
		}

		if (stack.size() != 1) {
			throw new java.lang.ArithmeticException("" + (stack.size() - 1) + " too few operators.");
		}

		return stack.pop();
	}

	private static int evaluateSingleOperator(char operation, int op1, int op2) {
		switch (operation) {
			case '+': return op1 + op2;
			case '-': return op1 - op2;
			case '*': return op1 * op2;
			case '/': return op1 / op2;
			default:  return 0;
		}
	}
}
