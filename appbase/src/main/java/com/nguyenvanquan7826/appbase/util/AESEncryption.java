package com.nguyenvanquan7826.appbase.util;

import android.util.Log;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {
    public static final String TAG = AESEncryption.class.getSimpleName();

    public static byte[] getKey() {
        byte arr[] = new byte[16];
        Arrays.fill(arr, (byte) '0');
        return arr;
    }

    public static byte[] getIV() {
        byte arr[] = new byte[16];
        Arrays.fill(arr, (byte) '0');
        return arr;
    }

    public static byte[] encrypt(String text) {
        return encrypt(text, getKey(), getIV());
    }

    public static byte[] encrypt(byte[] data) {
        return encrypt(data, getKey(), getIV());
    }

    public static byte[] encrypt(String plaintext, byte[] key, byte[] IV) {
        return encrypt(plaintext.getBytes(), key, IV);
    }

    public static byte[] encrypt(byte[] data, byte[] key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            // Create IvParameterSpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            // Initialize Cipher for ENCRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            // Perform Encryption
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static String decrypt(byte[] data) {
        return decrypt(data, getKey(), getIV());
    }
    public static byte[] decrypt1(byte[] data) {
        return decrypt1(data, getKey(), getIV());
    }

    public static String decrypt(byte[] cipherText, byte[] key, byte[] IV) {
        return new String(decrypt1(cipherText, key, IV));
    }

    public static byte[] decrypt1(byte[] cipherText, byte[] key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // Create SecretKeySpec
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            // Create IvParameterSpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            // Initialize Cipher for DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            // Perform Decryption
            byte[] decryptedText = cipher.doFinal(cipherText);
            return decryptedText;
        } catch (Exception e) {
            Log.e(TAG, "Error AES Decript:" + e.getMessage());
        }
        return new byte[0];
    }
}