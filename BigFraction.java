import java.math.BigInteger;

/**
 * A simple implementation of BigFractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Julian Kim
 * @version 1.2 of August 2023
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented with a
   * negative numerator. Similarly, if a fraction has a negative numerator, it is negative.
   * 
   * (2) BigFractions are not necessarily stored in simplified form. To obtain a fraction in
   * simplified form, one must call the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   */
  public BigFraction(String str) {
    String[] splitStr = str.split("/");
    this.num = new BigInteger(splitStr[0]);
    this.denom = new BigInteger(splitStr[1]);
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Simplify this fraction and return it.
   */
  public BigFraction simplify() {
    BigInteger ndGcd = this.num.gcd(this.denom);

    return new BigFraction(this.num.divide(ndGcd), this.denom.divide(ndGcd)).fixFraction();
  }

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).fixFraction();
  }// add(Fraction)

  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // if the denom is 1
    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    }

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  // multiplies this fraction by a different fraction
  public BigFraction multiply(BigFraction second) {
    // BigFraction result = new BigFraction(1,1);
    // result.num = first.num.multiply(second.num);
    // result.denom = first.denom.multiply(second.denom);
    // return result;

    return new BigFraction(this.num.multiply(second.num), this.denom.multiply(second.denom))
        .fixFraction();
  }

  // divides this fraction by a different fraction.
  public BigFraction divide(BigFraction second) {
    return new BigFraction(this.num.multiply(second.denom), this.denom.multiply(second.num))
        .fixFraction();
  }

  // subtracts a different fraction from this fraction.
  public BigFraction subtract(BigFraction subMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(subMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(subMe.denom)).subtract(subMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).fixFraction();
  }

  // returns only the fractional part of this (potentially mixed) fraction.
  public BigFraction fractional() {
    return new BigFraction(this.num.mod(this.denom), this.denom);
  }

  // fix negation problems with fractions (i.e. denominator is negative)
  private BigFraction fixFraction() {
    BigInteger newNum = this.num;
    BigInteger newDenom = this.denom;

    if (newDenom.compareTo(BigInteger.valueOf(0)) < 0) {
      newDenom = newDenom.negate();
      newNum = newNum.negate();
    }

    return new BigFraction(newNum, newDenom);
  }
} // class BigFraction
