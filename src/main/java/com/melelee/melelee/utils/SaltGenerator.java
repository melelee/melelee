package com.melelee.melelee.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * The type Salt generator.
 *
 * @author melelee
 */
public class SaltGenerator {

    /**
     * Generate salt string.
     *
     * @param length the length
     * @return the string
     */
    public static String generateSalt(int length) {
        //一个Byte占两个字节
        int byteLen = length >> 1;
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(byteLen).toHex();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(generateSalt(10));
    }
}
