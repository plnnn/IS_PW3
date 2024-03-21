package lt.viko.eif.nychyporuk.is_pw3_1;

import java.io.*;
import java.math.BigInteger;

public class Decryptor {

    private static BigInteger[] extendedGCD(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new BigInteger[]{a, BigInteger.ONE, BigInteger.ZERO};
        }
        else {
            BigInteger[] result = extendedGCD(b, a.mod(b));
            BigInteger gcd = result[0];
            BigInteger x = result[2];
            BigInteger y = result[1].subtract(a.divide(b).multiply(result[2]));
            return new BigInteger[]{gcd, x, y};
        }
    }

    private static BigInteger extendedEuclidean(BigInteger e, BigInteger phi) {
        BigInteger[] result = extendedGCD(e, phi);
        BigInteger d = result[1];
        if (d.compareTo(BigInteger.ZERO) < 0) {
            d = d.add(phi);
        }
        return d;
    }

    public static void decrypt() {
        String filePath = "encrypted.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            line = reader.readLine();
            BigInteger encrypted = new BigInteger(line);

            line = reader.readLine();
            BigInteger N = new BigInteger(line);

            line = reader.readLine();
            BigInteger E = new BigInteger(line);

            reader.close();


            BigInteger P = BigInteger.TWO, Q = BigInteger.ONE;
            for (int i = 1; i <= 10000000; ++i) {
                if (N.remainder(P).equals(BigInteger.ZERO)) {
                    Q = N.divide(P);
                    break;
                }
                P = P.add(BigInteger.ONE);
            }

            if (Q.equals(BigInteger.ONE)) {
                System.out.println("couldn't factorize n");
                return;
            }

            BigInteger P1 = P.subtract(BigInteger.ONE);
            BigInteger Q1 = Q.subtract(BigInteger.ONE);
            BigInteger PHI = P1.multiply(Q1);

            BigInteger D = extendedEuclidean(E, PHI);
            BigInteger decrypted = encrypted.modPow(D, N);


            try {
                byte[] bytesDecrypted = decrypted.toByteArray();

                String decryptedText = new String(bytesDecrypted);
                FileWriter myWriter = new FileWriter("decrypted.txt");
                myWriter.write(decryptedText + "\n");
                myWriter.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Decrypted message is successfully written to 'decrypted.txt'");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
