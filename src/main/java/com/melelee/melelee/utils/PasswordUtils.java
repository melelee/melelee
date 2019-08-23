package com.melelee.melelee.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * The type Password utils.
 */
public class PasswordUtils {

    /**
     * Encrypt password string.
     *
     * @param password the password
     * @param salt     the salt
     * @return the string
     */
    public static String encryptPassword(String password, String salt) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密2次
        int hashIterations = 2;

        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }
}
