import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Provides a main method that repeatedly reads user input, passes the input to BaseCalculator to
 * compute the result, then prints the result.
 * 
 * @author Julian Kim
 */
public class InteractiveCalculator extends BaseCalculator {
  public InteractiveCalculator(PrintWriter pen) {
    super(pen);
  }

  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BaseCalculator calc = new InteractiveCalculator(pen);

    while (calc.running) {
      pen.print("> ");
      pen.flush();
      pen.println(calc.evalCommand(input.readLine()));
    }
  }
}
