package com.okanbahadirsoygur.spring_boot_blog.library;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author okanbahadirsoygur
 * 04/04/2022
 * Burada şifreleme işlemleri yapılıyor.
 */
public  class SSecurity {


    /**
     * String bir ifadeyi md5 olarak encode eder.
     * @param string
     * @return
     */
    public String StringToMd5(String string) throws NoSuchAlgorithmException {

        String md5String = "";

        byte[] bytePasswd = string.getBytes(StandardCharsets.UTF_8);


        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] bytePasswdMD5 = md5.digest(bytePasswd);

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, bytePasswdMD5);

        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        md5String = hashtext;

        return md5String;
    }
}
