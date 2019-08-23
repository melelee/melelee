package com.melelee.melelee.controller;

import com.melelee.melelee.controller.bean.InWechatMsg;
import com.melelee.melelee.controller.bean.OutWechatMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping(value = "/wechat")
public class WechatController {
    @GetMapping
    public String token(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) throws NoSuchAlgorithmException {
        log.info("wechat toke auth begin signature {},timestamp {},nonce {},echostr {}", signature, timestamp, nonce, echostr);
        String[] arr = new String[]{signature, timestamp, nonce};
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        byte[] digest = messageDigest.digest(content.toString().getBytes());
        String tmpStr = byteToStr(digest);
        log.info(" tmpString {}, signature {}", tmpStr, signature);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        if (tmpStr.equalsIgnoreCase(signature)) {
            return echostr;
        } else {
            return "go fuck yourself!";
        }
    }

    @PostMapping
    public OutWechatMsg message(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce,
                                @RequestParam String echostr, @RequestBody InWechatMsg inWechatMsg) throws NoSuchAlgorithmException {
        log.info("wechat toke auth begin signature {},timestamp {},nonce {},echostr {}", signature, timestamp, nonce, echostr);
        String[] arr = new String[]{signature, timestamp, nonce};
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        byte[] digest = messageDigest.digest(content.toString().getBytes());
        String tmpStr = byteToStr(digest);
        log.info(" tmpString {}, signature {}");
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        if (tmpStr.equalsIgnoreCase(signature)) {
            //创建消息响应对象
            OutWechatMsg out = new OutWechatMsg();
            //把原来的发送方设置为接收方
            out.setToUserName(inWechatMsg.getFromUserName());
            //把原来的接收方设置为发送方
            out.setFromUserName(inWechatMsg.getToUserName());
            //获取接收的消息类型
            String msgType = inWechatMsg.getMsgType();
            //设置消息的响应类型
            out.setMsgType(msgType);
            //设置消息创建时间
            out.setCreateTime(new Date().getTime());
            //根据类型设置不同的消息数据
            if ("text".equals(msgType)) {
                out.setContent(inWechatMsg.getContent());
            } else if ("image".equals(msgType)) {
                out.setMediaId(new String[]{inWechatMsg.getMediaId()});
            }
            return out;
        } else {
            return null;
        }
    }

    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }

    private static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
