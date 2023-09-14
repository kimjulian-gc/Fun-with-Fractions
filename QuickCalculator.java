import java.io.PrintWriter;

/**
 * Provides a main method that takes expressions from the command line, passes the input to
 * BaseCalculator to compute the result, then prints the results.
 * 
 * @author Julian Kim
 */
public class QuickCalculator extends BaseCalculator {
  public QuickCalculator(PrintWriter pen) {
    super(pen);
  }

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    BaseCalculator calc = new QuickCalculator(pen);
    for (String arg : args) {
      String result = calc.evalCommand(arg);

      if (!calc.running)
        break;

      if (!result.equals("")) {
        pen.println(arg + " = " + result);
      }
    }
  }
}
