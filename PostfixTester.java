import java.util.Scanner;

public class PostfixTester {
    public static void main(String[] args) {
        String expression, again;    
        Scanner in = new Scanner(System.in);
      
        System.out.println("Postfix Evaluation – Team Member 1, Team Member 2, Team Member 3, ...");
        
        do {  
 			System.out.print("\nExpression: ");
            expression = in.nextLine();

            try {
            	System.out.println("Result = " + PostfixEvaluator.evaluate(expression));
            } 
            catch (ArithmeticException ae) {
            	System.out.println("ERROR: " + ae.getMessage());            	
            }

            System.out.print("\nEvaluate another expression [Y/N]? ");
            again = in.nextLine();
        }
        while (again.equalsIgnoreCase("y"));
        
        in.close();
  }
}
