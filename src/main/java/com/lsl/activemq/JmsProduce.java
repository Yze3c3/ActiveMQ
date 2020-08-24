package com.lsl.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
   消息生产者
 */
public class JmsProduce {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    private static  final String ACTIVEMQ_URL="tcp://192.168.72.128:61616";

    // 目的地的名称
    private static  final String QUEUE_NAME="jdbc";

    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);


        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);


        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Queue queue = session.createQueue(QUEUE_NAME);


        // 5 创建消息的生产者
        MessageProducer sessionProducer = session.createProducer(queue);

        sessionProducer.setDeliveryMode(DeliveryMode.PERSISTENT);


        // 6 通过sessionProducer生产 4 条 消息发送到消息队列中
        for (int i = 0; i <4 ; i++) {

            // 7  创建消息
            TextMessage textMessage = session.createTextMessage("msg---队列queue" + i);

            // 8  通过sessionProducer发送给mq
            sessionProducer.send(textMessage);

        }
        sessionProducer.close();
        session.commit();
        session.close();
        connection.close();
    }
}
