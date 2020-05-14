package Calculator;

public class Fraction {
    private int denominator;
    private int numerator;

    public Fraction(int numerator, int denominator){
        if(denominator == 0) {
            System.out.print(numerator + " "+ denominator);
            throw new IllegalArgumentException("Divide by 0 error");
        }
        if (denominator < 0){
            this.numerator = -1*numerator;
            this.denominator = -1*denominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
    //initializes the object equal in value to the integer parameter
    public Fraction(int num){
        this(num, 1);
    }
    //initializes the object to 0
    public Fraction(){
        this(0, 0);
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public String toString() {
        return numerator + " / " + denominator;
    }

    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    public Fraction add(Fraction other) {
        int addNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
        int addDenominator = (this.denominator * other.denominator);
        Fraction myFrac = new Fraction(addNumerator, addDenominator);
        myFrac.toLowestTerms();
        return myFrac;
    }

    public Fraction subtract(Fraction other) {
        int subNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
        int subDenominator = (this.denominator * other.denominator);
        Fraction myFrac = new Fraction(subNumerator, subDenominator);
        myFrac.toLowestTerms();
        return myFrac;
    }

    public Fraction multiply(Fraction other) {
        int multNumerator = (this.numerator * other.numerator);
        int multDenominator = (this.denominator * other.denominator);
        Fraction myFrac = new Fraction(multNumerator, multDenominator);
        myFrac.toLowestTerms();
        return myFrac;
    }

    public Fraction divide(Fraction other) {
        int divNumerator = (this.numerator * other.denominator);
        int divDenominator = (this.denominator * other.denominator);
        Fraction myFrac = new Fraction(divNumerator, divDenominator);
        return myFrac;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Fraction)) {
            return false;
        } else {
            this.toLowestTerms();
            ((Fraction) other).toLowestTerms();
            if ((this.numerator == ((Fraction) other).numerator) && this.denominator == ((Fraction) other).denominator) {
                return true;
            }
        }
        return false;
    }

    public void toLowestTerms() {
        int commonDenominator = gcd(this.numerator, this.denominator);
        numerator = this.numerator / commonDenominator;
        denominator = this.denominator / commonDenominator;
    }

    public int gcd(int num, int den) {
        while (num != 0 && den != 0) {
            int remain = num % den;
            num = den;
            den = remain;
        }
        return num;
    }
}
