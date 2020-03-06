package com.wb.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 炫龙 on 2020/2/16.
 */
public class Producer04_topics {

    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = null;
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器
            //创建与RabbitMQ服务的TCP连接
            connection = factory.newConnection();
            //创建与Exchange的通道，每个连接可以创建多个通道，每个通道代表一个会话任务
            channel = connection.createChannel();
            //声明交换机 String exchange, BuiltinExchangeType type
            /*** 参数明细
             *  1、交换机名称
             *  2、交换机类型，fanout、topic、direct、headers
             *  */
            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);

            //声明队列
            // (String queue, 队列名称
            // boolean durable, 是否持久华
            // boolean exclusive,  是否独占此队列
            // boolean autoDelete, 是否自动删除
            // Map<String, Object> arguments)  参数
            channel.queueDeclare(QUEUE_INFORM_EMAIL,true,false,false,null);
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);

        /*    channel.exchangeBind(QUEUE_INFORM_EMAIL,EXCHANGE_TOPICS_INFORM,"inform.#.email.#");
            channel.exchangeBind(QUEUE_INFORM_SMS,EXCHANGE_TOPICS_INFORM,"inform.#.sms.#");*/
            //发送邮件
            for (int i=0;i<10;i++){
                String message = "email 邮箱 inform to user"+i;
                //向交换机发送消息 String exchange, String routingKey, BasicProperties props,邮件发送消费者 byte[] bod

                /*** 参数明细
                 *  1、交换机名称，不指令使用默认交换机名称 Default Exchange
                 * 2、routingKey（路由key），根据key名称将消息转发到具体的队列，这里填写队列名称表示消 息将发到此队列 *
                 *  3、消息属性
                 *  4、消息内容
                 *  */

                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.email",null,message.getBytes());
                System.out.println("Send Message is:'" + message + "'");

            }


            //发送短信
            for (int i=0;i<10;i++){
                String message = "sms 短信 inform to user"+i;
                //向交换机发送消息 String exchange, String routingKey, BasicProperties props,邮件发送消费者 byte[] bod

                /*** 参数明细
                 *  1、交换机名称，不指令使用默认交换机名称 Default Exchange
                 * 2、routingKey（路由key），根据key名称将消息转发到具体的队列，这里填写队列名称表示消 息将发到此队列 *
                 *  3、消息属性
                 *  4、消息内容
                 *  */

                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.sms",null,message.getBytes());
                System.out.println("Send Message is:'" + message + "'");

            }
            //发送邮件和信息
            for (int i=0;i<10;i++){
                String message = "sms 短信 email 邮箱 inform to user"+i;
                //向交换机发送消息 String exchange, String routingKey, BasicProperties props,邮件发送消费者 byte[] bod

                /*** 参数明细
                 *  1、交换机名称，不指令使用默认交换机名称 Default Exchange
                 * 2、routingKey（路由key），根据key名称将消息转发到具体的队列，这里填写队列名称表示消 息将发到此队列 *
                 *  3、消息属性
                 *  4、消息内容
                 *  */

                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.sms.email",null,message.getBytes());
                System.out.println("Send Message is:'" + message + "'");

            }




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    
}
