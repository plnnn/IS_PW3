package lt.viko.eif.nychyporuk.is_pw3_1;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;


public class Encryptor {

    public static void encrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        String plaintext = scanner.nextLine();
        byte[] plaintextBytes = plaintext.getBytes();
        BigInteger msg = new BigInteger(plaintextBytes);

        System.out.print("Enter p: ");
        BigInteger P = scanner.nextBigInteger();

        System.out.print("Enter q: ");
        BigInteger Q = scanner.nextBigInteger();


        BigInteger N = P.multiply(Q);

        BigInteger P1 = P.subtract(BigInteger.ONE);
        BigInteger Q1 = Q.subtract(BigInteger.ONE);
        BigInteger PHI = P1.multiply(Q1);

        BigInteger E = BigInteger.TWO;
        while (true) {
            BigInteger G = E.gcd(PHI);
            if (G.equals(BigInteger.ONE)) break;
            E = E.add(BigInteger.ONE);
        }

        BigInteger encrypted = msg.modPow(E, N);


        try {
            FileWriter myWriter = new FileWriter("encrypted.txt");
            myWriter.write(encrypted + "\n");
            myWriter.write(N + "\n");
            myWriter.write(E + "\n");
            myWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Encrypted text and public key were successfully written to 'encrypted.txt'");
    }
}
