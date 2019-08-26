package com.melelee.melelee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melelee.melelee.controller.bean.InWechatMsg;
import com.melelee.melelee.controller.bean.OutWechatMsg;
import com.melelee.melelee.exception.AesException;
import com.melelee.melelee.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping(value = "/wechat")
public class WechatController {
    @GetMapping
    public String token(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) throws NoSuchAlgorithmException, AesException {
        log.info("wechat toke auth begin signature {},timestamp {},nonce {},echostr {}", signature, timestamp, nonce, echostr);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        if (PasswordUtils.verifyUrl(signature, timestamp, nonce)) {
            return echostr;
        } else {
            return "go fuck yourself!";
        }
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public OutWechatMsg message(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce,
                                @RequestBody InWechatMsg inWechatMsg) throws AesException, JsonProcessingException {

        log.info("wechat toke auth begin signature {},timestamp {},nonce {},message {}", signature, timestamp, nonce,
                new ObjectMapper().writeValueAsString(inWechatMsg));


        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        if (PasswordUtils.verifyUrl(signature, timestamp, nonce)) {
            //创建消息响应对象
            OutWechatMsg outWechatMsg = new OutWechatMsg();
            //把原来的发送方设置为接收方
            outWechatMsg.setToUserName(inWechatMsg.getFromUserName());
            //把原来的接收方设置为发送方
            outWechatMsg.setFromUserName(inWechatMsg.getToUserName());
            //获取接收的消息类型
            String msgType = inWechatMsg.getMsgType();
            //设置消息的响应类型
            outWechatMsg.setMsgType(msgType);
            //设置消息创建时间
            outWechatMsg.setCreateTime(new Date().getTime());
            //根据类型设置不同的消息数据
            if ("text".equals(msgType)) {
                outWechatMsg.setContent(inWechatMsg.getContent());
            } else if ("image".equals(msgType)) {
                outWechatMsg.setMediaId(new String[]{inWechatMsg.getMediaId()});
            }
            return outWechatMsg;
        } else {
            return null;
        }
    }
}
