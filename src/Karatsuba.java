import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Created by Tyler on 7/8/2017.
 */
public class Karatsuba {
    //due to the input being incredibly large, BigIntegers must be used instead of int
    public static BigInteger karatsubaMultiply(BigInteger x, BigInteger y) {
        BigInteger a;
        BigInteger b;
        BigInteger c;
        BigInteger d;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int lengthX = stringX.length();
        int lengthY = stringY.length();
        int maxLength = Math.max(lengthX,lengthY);

        //base case, multiplication with one digit is fine
        if (maxLength == 1) {
            return x.multiply(y);
        }

        //now pad the two strings to be the same length if multiplying numbers where lengthX and lengthY are not equal
        if (lengthX > lengthY) {
            String formatter = "%0";
            formatter += String.valueOf(lengthX);
            formatter += "d";
            stringY = String.format(formatter, new BigInteger(stringY));
            lengthY = stringY.length();
        }

        if (lengthY > lengthX) {
            String formatter = "%0";
            formatter += String.valueOf(lengthY);
            formatter += "d";
            stringX = String.format(formatter, new BigInteger(stringX));
            lengthX = stringX.length();
        }

        //now establish A/B/C/D
        a = new BigInteger(stringX.substring(0, maxLength / 2));
        b = new BigInteger(stringX.substring(maxLength / 2));
        c = new BigInteger(stringY.substring(0, maxLength / 2));
        d = new BigInteger(stringY.substring(maxLength / 2));

        //perform steps of Karatsuba algorithm
        BigInteger ac = karatsubaMultiply(a, c);
        BigInteger bd = karatsubaMultiply(b, d);
        BigInteger abcd = karatsubaMultiply((a.add(b)), (c.add(d)));
        BigInteger gauss  = abcd.subtract(bd).subtract(ac);

        BigInteger sum1;
        BigInteger sum2;

        if (stringX.length() % 2 == 0) {
            sum1 = ac.multiply(BigInteger.valueOf(10).pow(maxLength));
            sum2 = gauss.multiply(BigInteger.valueOf(10).pow(maxLength/2));
        }
        else {
           sum1 = ac.multiply(BigInteger.valueOf(10).pow(lengthX+1));
           sum2 = gauss.multiply(BigInteger.valueOf(10).pow(lengthX/2 +1));
        }

        return sum1.add(sum2).add(bd);
    }

    public static void testKaratsuba(BigInteger x, BigInteger y) {
        System.out.println(x.toString()+"x"+y.toString()+"="+karatsubaMultiply(x,y));
    }
    public static void test() {
        testKaratsuba(BigInteger.valueOf(9),BigInteger.valueOf(34));
        testKaratsuba(BigInteger.valueOf(154),BigInteger.valueOf(112));
        testKaratsuba(BigInteger.valueOf(8798),BigInteger.valueOf(349));
        testKaratsuba(BigInteger.valueOf(91),BigInteger.valueOf(9));
        testKaratsuba(BigInteger.valueOf(9584),BigInteger.valueOf(1239));
        testKaratsuba(BigInteger.valueOf(9123123),BigInteger.valueOf(877));
        testKaratsuba(BigInteger.valueOf(94343),BigInteger.valueOf(97878786));
        testKaratsuba(BigInteger.valueOf(129),BigInteger.valueOf(9676767));

    }
    public static void main(String[] args) {
        test();
    }
}

