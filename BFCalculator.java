import java.util.HashMap;
import java.util.Map;

public class BFCalculator {
  private Map<Character, BigFraction> namedRegister;
  private BigFraction lastValue;

  private enum Operator {
    ADD,
    SUB,
    MUL,
    DIV
  }

  public BFCalculator() {
    this.namedRegister = new HashMap<Character, BigFraction>();
  }

  public BigFraction evaluate(String exp) {
    String[] expArr = exp.split(" ");
    BigFraction evalFraction = new BigFraction(0, 1);
    Operator currOp = Operator.ADD;

    for (int i = 0; i < expArr.length; i++) {
      String curr = expArr[i];

      if (curr.indexOf("/") != -1) {
        evalFraction = applyOperator(
          evalFraction,
          new BigFraction(curr),
          currOp
        );
      } else if (isInt(curr)) {
        evalFraction = applyOperator(
          evalFraction,
          new BigFraction(Integer.parseInt(curr), 1),
          currOp
        );
      } else if (curr.length() == 1) {
        char currChar = curr.charAt(0);

        if (isAlpha(currChar)) {
          evalFraction = applyOperator(
            evalFraction,
            this.namedRegister.get(currChar),
            currOp
          );
        } else currOp = getOperator(currChar);
      }
    }

    this.lastValue = evalFraction;
    return this.lastValue;
  }

  public void store(char register) {

  }

  private static boolean isInt(String str) {
    char[] strArr = str.toCharArray();
    boolean strIsInt = true;

    for (int i = 0; i < strArr.length && strIsInt; i++) {
      int currShifted = strArr[i] - '0';
      strIsInt = currShifted >= 0 && currShifted < 10;
    }

    return strIsInt;
  }

  private static boolean isAlpha(char ch) {
    int charShifted = ch - 'a';
    return charShifted >= 0 && charShifted < 26;
  }

  private static Operator getOperator(char op) {
    switch (op) {
      case '+':
        return Operator.ADD;
      case '-':
        return Operator.SUB;
      case '*':
        return Operator.MUL;
      case '/':
        return Operator.DIV;
    }

    // should be unreachable...
    return Operator.ADD;
  }

  private static BigFraction applyOperator(BigFraction oldFrac, BigFraction newFrac, Operator op) {
    BigFraction result = oldFrac;

    switch(op) {
      case ADD:
        oldFrac.add(newFrac);
      case SUB:
        oldFrac.subtract(newFrac);
      case MUL:
        oldFrac.multiply(newFrac);
      case DIV:
        oldFrac.divide(newFrac);
    }

    return result;
  }
}
