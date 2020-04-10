package com.wwy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Sink.class)
public class ReceiveeMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    //接收yml中设置exchange中的消息，并会自定义一个queue，收到消息就会调用这个方法消费
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号,----->收到了： "+message.getPayload()+"\t port: "+serverPort);
    }
}
