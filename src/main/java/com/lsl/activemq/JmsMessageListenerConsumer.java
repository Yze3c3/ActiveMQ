package com.lsl.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;


/**
 *  消息消费者采用异步监听的方式 （监听器onMessage()）
 */
public class JmsMessageListenerConsumer {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    private static  final String ACTIVEMQ_URL="tcp://192.168.72.128:61616";

    // 目的地的名称
    private static  final String QUEUE_NAME="jdbc";
    public static void main(String[] args) throws JMSException, IOException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Queue queue = session.createQueue(QUEUE_NAME);

        // 5  创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

         /*   通过监听的方式来消费消息，是异步非阻塞的方式消费消息。
              通过messageConsumer 的setMessageListener注册一个监听器，
             当有消息发送来时，系统自动调用MessageListener的onMessage方法处理消息
         */
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if(null!=message && message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                        try {
                            System.out.println("****消费者接收到消息："+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }

                }

            }
        });

        // 让主线程不要结束。因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
        // 实际开发中，我们的程序会一直运行，这句代码都会省略。
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
