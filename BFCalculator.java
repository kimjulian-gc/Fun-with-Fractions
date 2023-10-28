import java.util.HashMap;
import java.util.Map;

/**
 * The primary workhorse! Evaluates expression strings and has a store register.
 * 
 * @author Julian Kim
 */
public class BFCalculator {
  private Map<Character, BigFraction> namedRegister;
  private BigFraction lastValue;

  private enum Operator {
    ADD, SUB, MUL, DIV, None
  }

  public BFCalculator() {
    this.namedRegister = new HashMap<Character, BigFraction>();
    this.lastValue = new BigFraction(0, 1);
  }

  // evaluates a string expression representing an expression dealing with big
  // fractions.
  public BigFraction evaluate(String exp) {
    String[] expArr = exp.split(" ");
    Operator currOp = Operator.ADD;
    boolean prevWasOp = true;

    BigFraction evalFraction = new BigFraction(0, 1);

    for (int i = 0; i < expArr.length; i++) {
      String curr = expArr[i];

      if (curr.length() == 1) {
        char currChar = curr.charAt(0);
        Operator possibleOp = getOperator(currChar);

        if (!possibleOp.equals(Operator.None)) {
          if (prevWasOp) return null;

          currOp = possibleOp;
          prevWasOp = true;
          continue;
        }
      }

      if (!prevWasOp) return null;

      BigFraction currFrac = getFracValue(curr);
      if (currFrac == null) return null;

      evalFraction = applyOperator(evalFraction, getFracValue(curr), currOp);
      prevWasOp = false;
    }

    if (prevWasOp) return null;

    this.lastValue = evalFraction.simplify();
    return this.lastValue;
  }

  // store the last calcualted value into the register
  public void store(char register) {
    this.namedRegister.put(register, this.lastValue);
  }

  // checks if the string is an integer
  private static boolean isInt(String str) {
    char[] strArr = str.toCharArray();
    boolean strIsInt = true;

    char firstCh = strArr[0];
    if (firstCh != '-' && !isDigit(firstCh)) strIsInt = false;

    for (int i = 1; i < strArr.length && strIsInt; i++) {
      strIsInt = isDigit(strArr[i]);
    }

    return strIsInt;
  }

  // returns if the character input is a digit, a numeric character between 0-9.
  private static boolean isDigit(int ch) {
    int chShifted = ch - '0';
    return chShifted >= 0 && chShifted <= 9;
  }

  // parses the string and turns it into a fraction.
  // warning! output can be null.
  private BigFraction getFracValue(String value) {
    if (value.indexOf("/") != -1)
      return new BigFraction(value);
    if (isInt(value))
      return new BigFraction(value + "/1");
    
    if (value.length() == 1) {
      BigFraction possibleReg = this.namedRegister.get(value.charAt(0));
      return (possibleReg == null) ? new BigFraction(0, 1) : possibleReg;
    }
    
    return null;
  }

  // private static boolean isAlpha(char ch) {
  //   int charShifted = ch - 'a';
  //   return charShifted >= 0 && charShifted < 26;
  // }

  // turns a possible operator character into an Operator
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

    return Operator.None;
  }

  // apply the operator onto the two fractions
  private static BigFraction applyOperator(BigFraction oldFrac, BigFraction newFrac, Operator op) {
    BigFraction result = oldFrac;

    switch (op) {
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
      case None:
        // should never be applied.
        result = oldFrac.add(newFrac);
        break;
    }

    return result;
  }
}
