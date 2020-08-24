package com.lsl.activemq;



import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsummer_Topicpersistence {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    private static  final String ACTIVEMQ_URL="tcp://192.168.72.128:61616";

    // 主题的名称
    private static  final String TOPIC_NAME="topic-jdbc-persistent";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        // 设置客户端ID。向MQ服务器注册自己的名称
        connection.setClientID("JAVAEE");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


        Topic topic = session.createTopic(TOPIC_NAME);

        // 创建一个topic订阅者对象。一参是topic，二参是订阅者名称
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark");

        // 之后再开启连接
        connection.start();



        while (true){
            Message message = subscriber.receive();
            TextMessage textMessage = (TextMessage)message;
            if(null !=textMessage) {
                System.out.println(" 收到的持久化 topic ：" + textMessage.getText());
            }else {
                break;
            }
         }
        session.close();
        connection.close();
    }
}
