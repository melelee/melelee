//package com.melelee.melelee.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//
///**
// * webSocket
// *
// * @author mengll
// * @create 2018-11-22 10:28
// **/
//@Component
//@ServerEndpoint(value = "/mychat/{usernick}")
//@Slf4j
//public class WebSocketController {
//
//    /**
//     * 连接事件 加入注解
//     * @param session
//     */
//    @OnOpen
//    public void onOpen(@PathParam(value = "usernick") String userNick, Session session) {
//        String message = "有新游客[" + userNick + "]加入聊天室!";
//        log.info(message);
//        WebSocketUtil.addSession(userNick, session);
//        //此时可向所有的在线通知 某某某登录了聊天室
//        WebSocketUtil.sendMessageForAll(message);
//    }
//
//    @OnClose
//    public void onClose(@PathParam(value = "usernick") String userNick,Session session) {
//        String message = "游客[" + userNick + "]退出聊天室!";
//        log.info(message);
//        WebSocketUtil.remoteSession(userNick);
//        //此时可向所有的在线通知 某某某登录了聊天室
//        WebSocketUtil.sendMessageForAll(message);
//    }
//
//    @OnMessage
//    public void OnMessage(@PathParam(value = "usernick") String userNick, String message) {
//        //类似群发
//        String info = "游客[" + userNick + "]：" + message;
//        log.info(info);
//        WebSocketUtil.sendMessageForAll(message);
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        log.error("异常:", throwable);
//        try {
//            session.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        throwable.printStackTrace();
//    }
//
//}
