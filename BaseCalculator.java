import java.io.PrintWriter;

/**
 * The backend behind InteractiveCalculator and QuickCalculator.
 * 
 * @author Julian Kim
 */
public class BaseCalculator {
  public BFCalculator bfCalc;
  public boolean running;
  public PrintWriter pen;

  public BaseCalculator() {
    this.bfCalc = new BFCalculator();
    this.running = true;
    this.pen = new PrintWriter(System.out, true);
  }

  public BaseCalculator(PrintWriter pen) {
    this.bfCalc = new BFCalculator();
    this.running = true;
    this.pen = pen;
  }

  // evaluates the inputted command
  public String evalCommand(String cmd) {
    String[] parsedCmd = cmd.split(" ");
    if (parsedCmd.length <= 2) {
      String possibleCmd = parsedCmd[0].toUpperCase();
      if (possibleCmd.equals("QUIT")) {
        this.running = false;
        return "";
      }
      if (possibleCmd.equals("STORE")) {
        char reg = parsedCmd[1].charAt(0);
        this.bfCalc.store(reg);
        return "";
      }
    }

    BigFraction output = this.bfCalc.evaluate(cmd);
    if (output == null)
      return "ERR! Expression has wrong form.";
    return output.toString();
  }
}
