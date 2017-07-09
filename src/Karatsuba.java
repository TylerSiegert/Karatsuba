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
        BigInteger step1;
        BigInteger step2;
        BigInteger step3;
        BigInteger step4;
        BigInteger one;
        BigInteger two;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int lengthX = stringX.length();
        int lengthY = stringY.length();

        //base case, multiplication with one digit is fine
        if (stringX.length() == 1 || stringY.length() == 1) {
            return x.multiply(y);
        }

        //now pad the two strings to be the same length
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
        a = new BigInteger(stringX.substring(0, lengthX / 2));
        b = new BigInteger(stringX.substring(lengthX / 2));
        c = new BigInteger(stringY.substring(0, lengthY / 2));
        d = new BigInteger(stringY.substring(lengthY / 2));


        step1 = karatsubaMultiply(a, c);
        step2 = karatsubaMultiply(b, d);
        step3 = karatsubaMultiply((a.add(b)), (c.add(d)));
        step4 = step3.subtract(step2).subtract(step1);

        BigInteger shift1 = BigInteger.valueOf(10).pow(lengthX);
        BigInteger shift2 = BigInteger.valueOf(10).pow(lengthX/2);


        if (stringX.length() % 2 == 0) {
            one = step1.multiply(shift1);
            two = step4.multiply(shift2);
        }
        else {
            one = step1.multiply(BigInteger.valueOf(10).pow(lengthX+1));
            two = step4.multiply(BigInteger.valueOf(10).pow(lengthX/2 +1));
        }

        return one.add(two).add(step2);
    }

    public static void test() {
        System.out.println("9x9=" + karatsubaMultiply(BigInteger.valueOf(9),BigInteger.valueOf(9)));
        System.out.println("2x4=" + karatsubaMultiply(BigInteger.valueOf(2),BigInteger.valueOf(4)));
        System.out.println("9x11=" + karatsubaMultiply(BigInteger.valueOf(9),BigInteger.valueOf(11)));
        System.out.println("14x89=" + karatsubaMultiply(BigInteger.valueOf(14),BigInteger.valueOf(89)));
        System.out.println("21x389=" + karatsubaMultiply(BigInteger.valueOf(21),BigInteger.valueOf(389)));
        System.out.println("814x7=" + karatsubaMultiply(BigInteger.valueOf(814),BigInteger.valueOf(7)));
        System.out.println("456x456=" + karatsubaMultiply(BigInteger.valueOf(456),BigInteger.valueOf(456)));
        System.out.println("7841x898=" + karatsubaMultiply(BigInteger.valueOf(7841),BigInteger.valueOf(898)));
        System.out.println("1345x1111=" + karatsubaMultiply(BigInteger.valueOf(1345),BigInteger.valueOf(1111)));
        System.out.println("134580x1111=" + karatsubaMultiply(BigInteger.valueOf(134580),BigInteger.valueOf(1111)));
        System.out.println("78956421x795461321=" + karatsubaMultiply(BigInteger.valueOf(78956421),BigInteger.valueOf(795461321)));
        String test1 = "7895642114";
        String test2 = "7954613215";
        System.out.println(test1+"x"+test2+"=" + karatsubaMultiply(new BigInteger(test1),new BigInteger(test2)));
        String largeInt1 = "3141592653589793238462643383279502884197169399375105820974944592";
        String largeInt2 = "2718281828459045235360287471352662497757247093699959574966967627";
        System.out.println(largeInt1+"x"+largeInt2+"=" + karatsubaMultiply(new BigInteger(largeInt1),new BigInteger(largeInt2)));
//        String largeTest1 = "7879654631213213";
//        String largeTest2 = "8461843218749843";
//        System.out.println(largeTest1+"x"+largeTest2+"=" + karatsubaMultiply(new BigInteger(largeTest1),new BigInteger(largeTest2)));

//        System.out.println(largeInt1+"x"+2+"=" + karatsubaMultiply(new BigInteger(largeInt1),BigInteger.valueOf(2)));


    }
    public static void main(String[] args) {
        test();
    }
}

