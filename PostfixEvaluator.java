
import java.util.Scanner;
import java.util.stream.LongStream;

public class PostfixEvaluator {
	public static int evaluate(String expr) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(100);
		Scanner parser = new Scanner(expr);

		while (parser.hasNext()) {
			String token = parser.next();
			if ("~!".indexOf(token) >= 0) {
				Integer op1 = stack.pop();
				if (op1 == null) {
					throw new java.lang.ArithmeticException("Insufficient operands for " + token + ".");
				}
				stack.push(evaluateUnaryOperator(token.charAt(0), op1));
			} else if ("+-*/%^<>=&|".indexOf(token) >= 0) {
				Integer op2 = stack.pop();
				Integer op1 = stack.pop();
				if (op1 == null || op2 == null) {
					throw new java.lang.ArithmeticException("Insufficient operands for " + token + ".");
				}
				stack.push(evaluateSingleOperator(token.charAt(0), op1, op2));
			} else if ("?".indexOf(token) >= 0) {
				Integer op3 = stack.pop();
				Integer op2 = stack.pop();
				Integer op1 = stack.pop();
				if (op1 == null || op2 == null|| op3 == null) {
					throw new java.lang.ArithmeticException("Insufficient operands for " + token + ".");
				}
				stack.push(evaluateTernaryOperator(token.charAt(0), op1, op2, op3));
			} else {
				stack.push(Integer.parseInt(token));
			}
		}

		if (stack.size() != 1) {
			throw new java.lang.ArithmeticException("" + (stack.size() - 1) + " too few operators.");
		}

		return stack.pop();
	}
	
	private static int evaluateUnaryOperator(char operation, int op1) {
		switch (operation) {
			case '~': return op1 * -1;
			case '!': return (int) LongStream.rangeClosed(1, op1).reduce(1, (long x, long y) -> x * y);
			default: return 0;
		}
	}
	
	private static int evaluateTernaryOperator(char operation, int op1, int op2, int op3) {
		switch (operation) {
			case '?': if (op1 == 1) {return op2;} else {return op3;}
			default: return 0;
		}
	}

	private static int evaluateSingleOperator(char operation, int op1, int op2) {
		switch (operation) {
			case '+': return op1 + op2;
			case '-': return op1 - op2;
			case '*': return op1 * op2;
			case '/': return op1 / op2;
			case '%': return op1 % op2;
			case '^': return (int) Math.pow(op1,op2);
			case '<': if (op1 < op2) {return 1;} else return 0;
			case '>': if (op1 > op2) {return 1;} else return 0;
			case '=': if (op1 == op2) {return 1;} else return 0;
			case '&': if (op1 != 0 && op2 != 0) {return 1;} else return 0;
			case '|': if (op1 == 1 || op2 == 1) {return 1;} else return 0;
			default:  return 0;
		}
	}
}
