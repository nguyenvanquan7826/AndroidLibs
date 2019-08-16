package com.nguyenvanquan7826.appbase.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteUtil {
    public static byte[] copyByte(byte[] src, int srcBegin, int srcEnd) {
        byte[] dest = new byte[srcEnd - srcBegin];
        for (int i = srcBegin, j = 0; i < srcEnd; i++, j++) dest[j] = src[i];
        return dest;
    }

    public static byte[] concatByte(byte[] arr1, byte[] arr2) {
        byte[] dest = new byte[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) dest[i] = arr1[i];
        for (int i = arr1.length, j = 0; j < arr2.length; i++, j++) dest[i] = arr2[j];
        return dest;
    }

    public static String bytesToHex(byte[] data) {
        StringBuilder hex = new StringBuilder();
        for (byte datum : data) {
            hex.append(String.format("%02X", datum));
        }
        return hex.toString();
    }


    public static byte[] intToByte(int x) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(x).array();
    }

    public static int byteToInt(byte[] data) {
        return ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
}
