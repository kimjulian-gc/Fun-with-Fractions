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
    this.lastValue = new BigFraction(0, 1);
  }

  public BigFraction evaluate(String exp) {
    String[] expArr = exp.split(" ");
    Operator currOp = Operator.ADD;

    BigFraction evalFraction = this.lastValue;
    String firstParsed = expArr[0];
    if (firstParsed.indexOf("/") != -1)
      evalFraction = new BigFraction(firstParsed);
    else if (isInt(firstParsed))
      evalFraction = new BigFraction(expArr[0] + "/1");
    else if (firstParsed.length() == 1)
      evalFraction = this.namedRegister.get(firstParsed.charAt(0));

    for (int i = 1; i < expArr.length; i++) {
      String curr = expArr[i];
      BigFraction toApply = null;

      if (isInt(curr)) {
        toApply = new BigFraction(Integer.parseInt(curr), 1);
      } else if (curr.length() == 1) {
        char currChar = curr.charAt(0);

        if (isAlpha(currChar)) {
          toApply = this.namedRegister.get(currChar);
        } else {
          currOp = getOperator(currChar);
        }
      } else {
        toApply = new BigFraction(curr);
      }

      if (toApply != null) {
        evalFraction = applyOperator(
          evalFraction,
          toApply,
          currOp
        );
      }
    }

    this.lastValue = evalFraction;
    return this.lastValue;
  }

  public void store(char register) {
    this.namedRegister.put(register, this.lastValue);
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
        result = oldFrac.add(newFrac);
        break;
      case SUB:
        result = oldFrac.subtract(newFrac);
        break;
      case MUL:
        result = oldFrac.multiply(newFrac);
        break;
      case DIV:
        result = oldFrac.divide(newFrac);
        break;
    }

    return result;
  }
}
