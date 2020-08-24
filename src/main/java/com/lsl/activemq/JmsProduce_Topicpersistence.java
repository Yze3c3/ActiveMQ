package com.lsl.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_Topicpersistence {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    private static  final String ACTIVEMQ_URL="tcp://192.168.72.128:61616";

    // 主题的名称
    private static  final String TOPIC_NAME="topic-jdbc-persistent";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);


        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();



        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Topic topic = session.createTopic(TOPIC_NAME);


        MessageProducer messageProducer = session.createProducer(topic);


        // 设置持久化topic
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // 设置持久化topic之后再启动连接
        connection.start();

        for (int i = 0; i < 7; i++) {
            TextMessage textMessage = session.createTextMessage("topic_name--" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("  **** TOPIC_NAME消息发送到MQ完成 ****");
    }
}
