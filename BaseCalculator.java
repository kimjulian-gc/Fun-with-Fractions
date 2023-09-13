import java.io.PrintWriter;

public class BaseCalculator {
  public BFCalculator bfCalc;
  public boolean running;
  public PrintWriter pen;

  public BaseCalculator(){
    this.bfCalc = new BFCalculator();
    this.running = true;
    this.pen = new PrintWriter(System.out, true);
  }

  public BaseCalculator(PrintWriter pen) {
    this.bfCalc = new BFCalculator();
    this.running = true;
    this.pen = pen;
  }

  public void evalCommand(String cmd) {
    String[] parsedCmd = cmd.split(" ");
    if (parsedCmd.length <= 2) {
      String possibleCmd = parsedCmd[0].toUpperCase();
      if (possibleCmd.equals("QUIT")) {
        this.running = false;
        return;
      }
      if (possibleCmd.equals("STORE")) {
        char reg = parsedCmd[1].charAt(0);
        this.bfCalc.store(reg);
        return;
      }
    }
    
    String output = this.bfCalc.evaluate(cmd).toString();
    pen.println(output);
  } 
}
