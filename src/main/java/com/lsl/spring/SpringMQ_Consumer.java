package com.lsl.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer springMQ_consumer = (SpringMQ_Consumer) classPathXmlApplicationContext.getBean("springMQ_Consumer");

         String retValue = (String) springMQ_consumer.jmsTemplate.receiveAndConvert();

        System.out.println("消费者收到的信息："+retValue);

    }
}
