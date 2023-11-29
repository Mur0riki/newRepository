package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class MD5Hash {
    public String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            String myHash = bytesToHex(messageDigest);
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 должен быть поддержан вашей Java Virtual Machine.", e);
        }
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
