package com.booking.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility to hash a string with SHA-256 and return a lowercase hex digest.
 */
public class PasswordHasher {

    /** 
     * Compute SHA-256(input) as a hex string.
     */
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[]        hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // convert to hex
            StringBuilder sb = new StringBuilder(hashBytes.length * 2);
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 should always be available on a standard JDK
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
