package com.lsl.boot.activemq.produce;

import com.lsl.boot.activemq.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Queue_Produce {


    @Autowired
    private ConfigBean configBean;

    @Autowired
    private JmsMessagingTemplate  jmsMessagingTemplate;


    public void  produceMsg(){
        jmsMessagingTemplate.convertAndSend(configBean.queue(),"****"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("springboot消息生成成功");
    }


    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(configBean.queue(),"****"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("springboot定时生成消息成功");
    }
}
