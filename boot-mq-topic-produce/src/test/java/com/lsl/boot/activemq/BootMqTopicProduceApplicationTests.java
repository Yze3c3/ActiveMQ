package com.lsl.boot.activemq;

import com.lsl.boot.activemq.produce.Topic_Produce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootMqTopicProduceApplicationTests {


    @Autowired
    private Topic_Produce topic_produce;

    @Test
    void contextLoads() {
        topic_produce.produceTop();
    }
}
