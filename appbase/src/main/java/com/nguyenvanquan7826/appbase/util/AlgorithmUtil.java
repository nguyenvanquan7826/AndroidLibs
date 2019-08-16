package com.nguyenvanquan7826.appbase.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AlgorithmUtil {
    public static String getMD5EncryptedString(String encTarget) {
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        } // Encryption algorithm
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        StringBuilder md5 = new StringBuilder(new BigInteger(1, mdEnc.digest()).toString(16));
        while (md5.length() < 32) {
            md5.insert(0, "0");
        }
        return md5.toString();
    }
}