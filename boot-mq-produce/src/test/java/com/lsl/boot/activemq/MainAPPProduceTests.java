package com.lsl.boot.activemq;

import com.lsl.boot.activemq.produce.Queue_Produce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainAPPProduceTests {

    @Autowired
    private Queue_Produce queue_produce;

    @Test
    void contextLoads() {
     queue_produce.produceMsg();
    }
}
