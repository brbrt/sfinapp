package hu.rbr.sfinapp.core.cache;

import javax.ws.rs.core.EntityTag;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ETagCalculator {

    public static EntityTag calculate(String uri, String version) {
        String s = uri + " @v " + version;
        String hash = calculateHash(s);

        return new EntityTag(hash);
    }

    private static String calculateHash(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            return "";
        }
    }

}