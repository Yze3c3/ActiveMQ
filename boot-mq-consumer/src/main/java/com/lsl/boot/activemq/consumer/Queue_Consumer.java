package com.lsl.boot.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Queue_Consumer {

    @JmsListener(destination="${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {

        System.out.println("接收到的消息为："+textMessage.getText());
    }
}
