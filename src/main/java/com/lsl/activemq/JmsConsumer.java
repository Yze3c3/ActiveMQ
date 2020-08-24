package com.lsl.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  消息消费者  采用reveive() 一直等待接收消息，在能够接收到消息之前将一直阻塞。是同步阻塞方式
 */
public class JmsConsumer {
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

       // 5  创建消费者
        MessageConsumer sessionConsumer = session.createConsumer(queue);


        // 6 通过sessionConsumer接收消息
       while (true){
             // reveive() 一直等待接收消息，在能够接收到消息之前将一直阻塞。 是同步阻塞方式 。和socket的accept方法类似的。
            // reveive(Long time) : 等待n毫秒之后还没有收到消息，就是结束阻塞。
           // 因为消息发送者是 TextMessage，所以消息接受者也要是TextMessage
           TextMessage message = (TextMessage)sessionConsumer.receive(4000);
           if(message!=null){
               System.out.println("****消费者接收到消息："+message.getText());
           }else{
               break;
           }
       }
        sessionConsumer.close();
        session.commit();
        session.close();
        connection.close();
        System.out.println("  **** 消息发送到MQ完成 ****");
      }
    }

