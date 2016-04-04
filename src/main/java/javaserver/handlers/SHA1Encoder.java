package javaserver.handlers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Encoder {

    public static String generateSHA(byte[] fileContents) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(fileContents);
            return convertSHA1ToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            return "";
        }
    }

    private static String convertSHA1ToString(byte[] hash) {
        StringBuilder builder = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

}
