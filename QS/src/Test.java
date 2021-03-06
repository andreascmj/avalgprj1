import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void testLegendre() {


        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("2")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("3")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("5")) == -1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("7")) == -1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("11")) == -1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("13")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("17")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("19")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("23")) == -1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("29")) == 1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("31")) == -1);
        System.out.println(BigMath.legendre(new BigInteger("87463"), new BigInteger("37")) == -1);



	}

    public static BigInteger testTrialDivision(BigInteger n) throws FileNotFoundException {
//        BigInteger n = new BigInteger("837497897981234878");
        LinkedList<BigInteger> factors = TrialDivision.trialDivision(n);
        n = TrialDivision.divide(n, factors);

        System.err.println("N: " + n.toString());
        for (BigInteger f : factors)
            System.err.println("F: " + f.toString());

        return n;
    }

    public static void testPollardBrent(BigInteger n) {
//        BigInteger n = new BigInteger("762348787734763");
//        BigInteger n = new BigInteger("2637885078667");
        long t = System.currentTimeMillis();
        LinkedList<BigInteger> factors = PollardBrent.factor(n);
        System.out.println("Time is: " + (System.currentTimeMillis() - t));
        for (BigInteger f : factors)
            System.err.println("F: " + f.toString());
    }


    public static void testQS() {

        System.out.println(MillerRabin.isPrime(new BigInteger("3"), 5));

        BigInteger n = new BigInteger("920700765300");
        QS qs = new QS(n);

        qs.calculateBase();
        qs.calculateFactorBase();

        int rows = qs.getSmoothNumberSize();
        int columns = qs.getFactorBase().size();
        BitSet[] matrix = new BitSet[rows];

        qs.generateSmoothNumbers(matrix);

        matrix = Gauss.gaussEliminate(matrix, rows, columns);

        BitSet freeVar = Gauss.getFreeVariables(matrix, rows, columns);
        System.err.println("Free variables: ");
        Gauss.printBitSet(freeVar, columns);

        BitSet nullspace = null;
        Gauss.printMatrix(matrix, rows, columns);

        /*
        while (true) {
            nullspace = Gauss.calcNullSpace(matrix, rows, columns, freeVar, nullspace);

            BigInteger a = new BigInteger("1");
            BigInteger b = new BigInteger("1");
            for (int i = 0; i < rows; i++) {
                if (nullspace.get(i)) {
                    a = a.multiply(qs.getSmoothNumbers().get(i)).mod(n);
                    b = b.multiply(BigInteger.valueOf(qs.getFactorBase().get(i)));
                }
            }
            b = BigMath.sqrt(b);
//            System.err.println("a: " + a.toString() + " b: " + b.toString());
            BigInteger f1 = BigMath.gcd(a.subtract(b), n);
            BigInteger f2 = BigMath.gcd(a.add(b), n);
                System.err.println("f1: " + f1.toString() + " f2: " + f2.toString());
//            Gauss.printBitSet(nullspace, columns);
            if (nullspace.isEmpty()) {
                break;
            }
        }
                 */


        //System.out.println(hej.get(4));



    }


}
