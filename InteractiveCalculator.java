import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InteractiveCalculator extends BaseCalculator {
  public InteractiveCalculator() {
    super();
  }
  public InteractiveCalculator(PrintWriter pen) {
    super(pen);
  }

  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BaseCalculator calc = new InteractiveCalculator(pen);

    while(calc.running) {
      pen.print("> ");
      pen.flush();
      calc.evalCommand(input.readLine());
    }
  }
}
