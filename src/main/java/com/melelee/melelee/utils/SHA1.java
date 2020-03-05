package com.melelee.melelee.utils;

import com.melelee.melelee.exception.AesException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Arrays;

@Slf4j
public class SHA1 {

    /**
     * 用SHA1算法验证Token
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 安全签名
     * @throws AesException
     */
    public static String getSHA1(String token, String timestamp, String nonce) throws AesException {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder hexstr = new StringBuilder();
            String shaHex = "";
            for (byte aDigest : digest) {
                shaHex = Integer.toHexString(aDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            log.error("SHA1 error", e);
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
