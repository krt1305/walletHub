package util;

import java.util.Base64;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT=30;
    public static long IMPLICIT_WAIT=10;

    public static String decodePassword(String encodedPassword)
    {
        byte[] decodedPassword= Base64.getDecoder().decode(encodedPassword);
        System.out.println("DEcoded password "+new String(decodedPassword));
        return new String(decodedPassword);

    }




}
