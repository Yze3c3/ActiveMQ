package com.lsl.boot.activemq.produce;

import com.lsl.boot.activemq.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Topic_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private ConfigBean configBean;

    public void produceTop(){
        jmsMessagingTemplate.convertAndSend(configBean.topic(),"主题消息："+ UUID.randomUUID().toString().substring(0,6));

        System.out.println("springboot消息生成成功");
    }

    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(configBean.topic(),"****"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("springboot定时生成消息成功");
    }
}
