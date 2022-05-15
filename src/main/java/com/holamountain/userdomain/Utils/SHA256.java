package com.holamountain.userdomain.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String encrypt(String text) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
