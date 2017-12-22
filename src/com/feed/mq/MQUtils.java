package com.feed.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
//import org.clapper.util.logging.Logger;
 
//import com.pzoom.dsa.common.util.Log;
//import com.pzoom.dsa.nerd.mysql.DBQueryHelper;
 
public class MQUtils
{
  static ConnectionFactory connectionFactory;
  static Connection connection = null;
  static Session session;
  static Map<String, MessageProducer> sendQueues = new ConcurrentHashMap<String, MessageProducer>();
 
  static Map<String, MessageConsumer> getQueues = new ConcurrentHashMap<String, MessageConsumer>();
 
//  static Log log=Log.getLogger(DBQueryHelper.class);
 
  static {
    connectionFactory = new ActiveMQConnectionFactory(
      ActiveMQConnection.DEFAULT_USER, 
      ActiveMQConnection.DEFAULT_PASSWORD, 
      "tcp://localhost:61616?wireFormat.maxInactivityDuration=0");
    try
    {
      connection = connectionFactory.createConnection();
 
      connection.start();
 
      session = connection.createSession(Boolean.FALSE.booleanValue(),1);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  static MessageProducer getMessageProducer(String name) {
    if (sendQueues.containsKey(name))
      return ((MessageProducer)sendQueues.get(name));
    try
    {
      Destination destination = session.createQueue(name);
      MessageProducer producer = session.createProducer(destination);
      sendQueues.put(name, producer);
      return producer;
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return ((MessageProducer)sendQueues.get(name));
  }
 
  static MessageConsumer getMessageConsumer(String name) {
    if (getQueues.containsKey(name))
      return ((MessageConsumer)getQueues.get(name));
    try
    {
      Destination destination = session.createQueue(name);
      MessageConsumer consumer = session.createConsumer(destination);
      getQueues.put(name, consumer);
      return consumer;
    } catch (JMSException e) {
      e.printStackTrace();
    }
 
    return ((MessageConsumer)getQueues.get(name));
  }
 
  public static void sendMessage(String queue, String text) {
    try {
      TextMessage message = session.createTextMessage(text);
      getMessageProducer(queue).send(message);
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
  }
   
  
   
  public static List<TextMessage> getMessage(String queue)
  {
	  List<TextMessage> list=new ArrayList<TextMessage>();
    try {
    	while(true){
    		TextMessage message = (TextMessage)getMessageConsumer(queue).receive(10000L);
    		if (message == null) {
    			break;
    		}else{
    			list.add(message);
    		}
    	}
      return list;
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return null;
  }
 
  public static void close() {
    try {
      session.close();
    } catch (JMSException e) {
      e.printStackTrace();
    }
    try {
      connection.close();
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

public static ConnectionFactory getConnectionFactory() {
	return connectionFactory;
}

public static void setConnectionFactory(ConnectionFactory connectionFactory) {
	MQUtils.connectionFactory = connectionFactory;
}
  
  
  
  
  
}